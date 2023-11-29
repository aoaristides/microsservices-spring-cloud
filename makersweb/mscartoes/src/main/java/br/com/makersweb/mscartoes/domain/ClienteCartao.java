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
@Table(name = "clientes_cartoes")
@Data
@NoArgsConstructor
public class ClienteCartao {

    @Id
    private String id;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

    private BigDecimal limite;
    private String protocolo;

}
