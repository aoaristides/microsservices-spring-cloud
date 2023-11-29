package br.com.makersweb.msavaliadorcredito.services.impl;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroSolicitacaoCartaoException;
import br.com.makersweb.msavaliadorcredito.domain.model.*;
import br.com.makersweb.msavaliadorcredito.infrastructure.clients.CartaoResourceClient;
import br.com.makersweb.msavaliadorcredito.infrastructure.clients.ClienteResourceClient;
import br.com.makersweb.msavaliadorcredito.infrastructure.listner.publisher.SolicitacaoEmissaoCartaoPublisher;
import br.com.makersweb.msavaliadorcredito.services.AvaliadorCreditoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServiceImpl implements AvaliadorCreditoService {

    private final ClienteResourceClient clienteResource;
    private final CartaoResourceClient cartaoResource;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    @Override
    public SituacaoCliente obterSituacaoCliente(final String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException {
        try {
            final var dadosClienteResponse = clienteResource.dadosClientes(cpf);
            final var cartoesResponse = cartaoResource.getCartoesPorCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            final var status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicoException(e.getMessage(), status);
        }
    }

    @Override
    public RetornoAvaliacaoCliente realizarAvaliacao(final String cpf, final Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException {
        try {
            final var dadosClienteResponse = clienteResource.dadosClientes(cpf);
            final var cartoesResponse = cartaoResource.getCartoesRendaAte(renda);
            final var cliente = dadosClienteResponse.getBody();
            final var cartoes = cartoesResponse.getBody();
            final var cartaoAprovados = cartoes.stream().map(cartao -> {

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idade = BigDecimal.valueOf(cliente.getIdade());
                var fator = idade.divide(BigDecimal.TEN);
                var limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimite(limiteAprovado);
                return aprovado;
            }).collect(Collectors.toList());

            return new  RetornoAvaliacaoCliente(cartaoAprovados);
        } catch (FeignException.FeignClientException e) {
            final var status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicoException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            final var protocolo = UUID.randomUUID().toString();
            dados.setProtocolo(protocolo.toString());
            emissaoCartaoPublisher.solicitarCartao(dados);
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
