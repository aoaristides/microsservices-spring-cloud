package br.com.makersweb.msavaliadorcredito.infrastructure.clients;

import br.com.makersweb.msavaliadorcredito.domain.model.CartaoCliente;
import br.com.makersweb.msavaliadorcredito.domain.model.DadosCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author aaristides
 */
@FeignClient(value = "ms-cartoes", path = "/cartoes")
public interface CartaoResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesPorCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<DadosCartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);

}
