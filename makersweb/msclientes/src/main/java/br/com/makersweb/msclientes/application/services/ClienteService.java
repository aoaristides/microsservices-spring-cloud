package br.com.makersweb.msclientes.application.services;

import br.com.makersweb.msclientes.domain.Cliente;

import java.util.Optional;

/**
 * @author aaristides
 */
public interface ClienteService {

    Cliente salvar(final Cliente cliente);

    Optional<Cliente> buscarPorCpf(final String cpf);

}
