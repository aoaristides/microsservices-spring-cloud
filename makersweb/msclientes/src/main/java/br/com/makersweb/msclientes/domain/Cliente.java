package br.com.makersweb.msclientes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author aaristides
 */
@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private Integer idade;

    public Cliente(String nome, String cpf, Integer idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

}
