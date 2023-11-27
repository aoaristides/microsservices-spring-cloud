package br.com.makersweb.msclientes.infrastructure.repository;

import br.com.makersweb.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author aaristides
 */
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByCpf(final String cpf);

}
