package br.com.makersweb.msavaliadorcredito.application.exceptions;

/**
 * @author aaristides
 */
public class DadosClienteNotFoundException extends Exception {

    public DadosClienteNotFoundException() {
        super("Dados do Cliente não encontrados para o CPF informado.");
    }
}
