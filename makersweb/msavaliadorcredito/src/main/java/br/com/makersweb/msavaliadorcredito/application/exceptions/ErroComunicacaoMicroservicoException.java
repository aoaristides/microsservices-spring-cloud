package br.com.makersweb.msavaliadorcredito.application.exceptions;

import lombok.Getter;

/**
 * @author aaristides
 */
public class ErroComunicacaoMicroservicoException extends Exception {

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicoException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
