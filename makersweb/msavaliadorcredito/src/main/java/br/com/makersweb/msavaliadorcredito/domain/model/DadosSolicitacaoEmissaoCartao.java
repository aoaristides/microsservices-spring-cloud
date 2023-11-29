package br.com.makersweb.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
@Data
public class DadosSolicitacaoEmissaoCartao {

    private String cartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
    private String protocolo;

}
