package br.com.makersweb.mscartoes.application.dto;

import br.com.makersweb.mscartoes.domain.ClienteCartao;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param nome
 * @param bandeira
 * @param limite
 */
public record CartoesPorClienteResponse(
        String nome,
        String bandeira,
        BigDecimal limite
) {

    public static CartoesPorClienteResponse from(final ClienteCartao clienteCartao) {
        final var cartao = clienteCartao.getCartao();
        return new CartoesPorClienteResponse(cartao.getNome(), cartao.getBandeira().name(), clienteCartao.getLimite());
    }

}
