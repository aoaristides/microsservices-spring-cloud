package br.com.makersweb.msavaliadorcredito.services;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import br.com.makersweb.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao;
import br.com.makersweb.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import br.com.makersweb.msavaliadorcredito.domain.model.SituacaoCliente;

/**
 * @author aaristides
 */
public interface AvaliadorCreditoService {

    SituacaoCliente obterSituacaoCliente(final String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException;

    RetornoAvaliacaoCliente realizarAvaliacao(final String cpf, final Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException;

    ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados);

}
