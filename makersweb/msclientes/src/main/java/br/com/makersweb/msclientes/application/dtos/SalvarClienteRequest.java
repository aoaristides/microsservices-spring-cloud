package br.com.makersweb.msclientes.application.dtos;

import br.com.makersweb.msclientes.domain.Cliente;

/**
 * @author aaristides
 * @param nome
 * @param cpf
 * @param idade
 */
public record SalvarClienteRequest(
        String nome,
        String cpf,
        Integer idade
) {

    public Cliente toModel() {
        return new Cliente(nome(), cpf(), idade());
    }

}
