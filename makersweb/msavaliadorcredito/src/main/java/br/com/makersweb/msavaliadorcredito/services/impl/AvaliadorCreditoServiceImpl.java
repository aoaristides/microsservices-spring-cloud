package br.com.makersweb.msavaliadorcredito.services.impl;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.domain.model.SituacaoCliente;
import br.com.makersweb.msavaliadorcredito.infrastructure.clients.CartaoResourceClient;
import br.com.makersweb.msavaliadorcredito.infrastructure.clients.ClienteResourceClient;
import br.com.makersweb.msavaliadorcredito.services.AvaliadorCreditoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
public class AvaliadorCreditoServiceImpl implements AvaliadorCreditoService {

    private final ClienteResourceClient clienteResource;
    private final CartaoResourceClient cartaoResource;

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
}
