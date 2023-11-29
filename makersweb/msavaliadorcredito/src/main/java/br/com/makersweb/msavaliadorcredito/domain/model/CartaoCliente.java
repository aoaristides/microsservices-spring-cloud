package br.com.makersweb.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
@Data
public class CartaoCliente {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

}
