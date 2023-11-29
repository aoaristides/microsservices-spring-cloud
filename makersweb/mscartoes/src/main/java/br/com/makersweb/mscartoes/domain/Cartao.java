package br.com.makersweb.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author aaristides
 */
@Entity
@Table(name = "cartoes")
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    private String id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    private Cartao(String anId, String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.id = anId;
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

    public static Cartao newCartao(
            final String aNome,
            final String aBandeira,
            final BigDecimal aRenda,
            final BigDecimal aLimiteBasico
    ) {
        final var anId = CartaoID.unique().getValue();
        final var bandeira = BandeiraCartao.valueOf(aBandeira.toUpperCase());
        return new Cartao(anId, aNome, bandeira, aRenda, aLimiteBasico);
    }



}
