package br.com.makersweb.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
@Data
public class CartaoAprovado {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

}
