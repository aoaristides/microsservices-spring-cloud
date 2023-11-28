package br.com.makersweb.mscartoes.services;

import br.com.makersweb.mscartoes.domain.Cartao;

import java.util.List;

/**
 * @author aaristides
 */
public interface CartaoService {

    Cartao save(Cartao cartao);

    List<Cartao> getCartoesRendaMenorIgual(Long renda);


}
