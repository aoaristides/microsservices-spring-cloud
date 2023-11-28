package br.com.makersweb.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    private Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

    public static Cartao with(
            final String aNome,
            final String aBandeira,
            final BigDecimal aRenda,
            final BigDecimal aLimiteBasico
    ) {
        final var bandeira = BandeiraCartao.valueOf(aBandeira.toUpperCase());
        return new Cartao(aNome, bandeira, aRenda, aLimiteBasico);
    }

}
