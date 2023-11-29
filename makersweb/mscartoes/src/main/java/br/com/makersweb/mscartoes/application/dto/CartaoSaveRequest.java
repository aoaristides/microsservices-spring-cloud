package br.com.makersweb.mscartoes.application.dto;

import br.com.makersweb.mscartoes.domain.Cartao;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param nome
 * @param bandeira
 * @param renda
 * @param limite
 */
public record CartaoSaveRequest(
      String nome,
      String bandeira,
      BigDecimal renda,
      BigDecimal limite
) {

    public Cartao toModel() {
        return Cartao.newCartao(nome(), bandeira(), renda(), limite());
    }

}
