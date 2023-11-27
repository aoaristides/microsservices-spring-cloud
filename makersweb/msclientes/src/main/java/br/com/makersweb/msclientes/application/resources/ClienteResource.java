package br.com.makersweb.msclientes.application.resources;

import br.com.makersweb.msclientes.application.dtos.SalvarClienteRequest;
import br.com.makersweb.msclientes.application.services.ClienteService;
import br.com.makersweb.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody SalvarClienteRequest input) {
        final var cliente = input.toModel();
        clienteService.salvar(cliente);

        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> dadosClientes(@RequestParam("cpf") String cpf) {
        final var cliente = clienteService.buscarPorCpf(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

}
