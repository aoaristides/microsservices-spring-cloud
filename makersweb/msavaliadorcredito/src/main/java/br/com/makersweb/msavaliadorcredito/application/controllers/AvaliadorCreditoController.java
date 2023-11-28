package br.com.makersweb.msavaliadorcredito.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @GetMapping
    public String status() {
        return "OK";
    }

}
