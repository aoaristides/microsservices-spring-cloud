package br.com.makersweb.mscartoes.application.resources;

import br.com.makersweb.mscartoes.application.dto.CartaoSaveRequest;
import br.com.makersweb.mscartoes.domain.Cartao;
import br.com.makersweb.mscartoes.services.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoessResource {

    private final CartaoService cartaoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CartaoSaveRequest request) {
        final var cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        return ResponseEntity.ok(cartaoService.getCartoesRendaMenorIgual(renda));
    }

}
