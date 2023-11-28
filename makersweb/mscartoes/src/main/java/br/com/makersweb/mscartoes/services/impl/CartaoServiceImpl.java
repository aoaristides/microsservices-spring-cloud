package br.com.makersweb.mscartoes.services.impl;

import br.com.makersweb.mscartoes.domain.Cartao;
import br.com.makersweb.mscartoes.infrastructure.repository.CartaoRepository;
import br.com.makersweb.mscartoes.services.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository cartaoRepository;

    @Override
    public Cartao save(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    @Override
    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        final var rendaCartao = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaCartao);
    }
}
