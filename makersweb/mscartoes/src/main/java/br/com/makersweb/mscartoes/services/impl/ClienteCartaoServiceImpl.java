package br.com.makersweb.mscartoes.services.impl;

import br.com.makersweb.mscartoes.domain.ClienteCartao;
import br.com.makersweb.mscartoes.infrastructure.repository.ClienteCartaoRepository;
import br.com.makersweb.mscartoes.services.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
public class ClienteCartaoServiceImpl implements ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    @Override
    public List<ClienteCartao> listarCartoesPoCpf(final String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
