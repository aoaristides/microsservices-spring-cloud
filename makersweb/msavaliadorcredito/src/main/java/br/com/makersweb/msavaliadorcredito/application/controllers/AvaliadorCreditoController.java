package br.com.makersweb.msavaliadorcredito.application.controllers;

import br.com.makersweb.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import br.com.makersweb.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroservicoException;
import br.com.makersweb.msavaliadorcredito.domain.model.DadosAvaliacao;
import br.com.makersweb.msavaliadorcredito.domain.model.SituacaoCliente;
import br.com.makersweb.msavaliadorcredito.services.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "")
    public ResponseEntity<?> realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            return ResponseEntity.ok(avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda()));
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicoException e) {
            var status = HttpStatus.resolve(e.getStatus()) != null ? HttpStatus.resolve(e.getStatus()) : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(e.getMessage());
        }
    }

}
