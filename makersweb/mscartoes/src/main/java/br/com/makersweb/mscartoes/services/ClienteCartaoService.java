package br.com.makersweb.mscartoes.services;

import br.com.makersweb.mscartoes.domain.ClienteCartao;

import java.util.List;

/**
 * @author aaristides
 */
public interface ClienteCartaoService {

    List<ClienteCartao> listarCartoesPoCpf(final String cpf);

}
