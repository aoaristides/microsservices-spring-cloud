package br.com.makersweb.msclientes.application.services.impl;

import br.com.makersweb.msclientes.application.services.ClienteService;
import br.com.makersweb.msclientes.domain.Cliente;
import br.com.makersweb.msclientes.infrastructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
public class DefaultClienteService implements ClienteService {

    private final ClienteRepository repository;

    @Transactional
    @Override
    public Cliente salvar(final Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(final String cpf) {
        return repository.findByCpf(cpf);
    }
}
