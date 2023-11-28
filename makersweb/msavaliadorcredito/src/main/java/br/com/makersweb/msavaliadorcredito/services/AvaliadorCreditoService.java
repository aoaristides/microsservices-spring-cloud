package br.com.makersweb.msavaliadorcredito.services;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.domain.model.SituacaoCliente;

/**
 * @author aaristides
 */
public interface AvaliadorCreditoService {

    SituacaoCliente obterSituacaoCliente(final String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException;

}
