package br.com.makersweb.msavaliadorcredito.application.controllers;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.domain.model.SituacaoCliente;
import br.com.makersweb.msavaliadorcredito.services.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") final String cpf) {
        try {
            return ResponseEntity.ok(avaliadorCreditoService.obterSituacaoCliente(cpf));
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicoException e) {
            var status = HttpStatus.resolve(e.getStatus()) != null ? HttpStatus.resolve(e.getStatus()) : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(e.getMessage());
        }
    }

}
