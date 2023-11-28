package br.com.makersweb.msavaliadorcredito.infrastructure.clients;

import br.com.makersweb.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aaristides
 */
@FeignClient(value = "ms-clientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosClientes(@RequestParam("cpf") String cpf);

}
