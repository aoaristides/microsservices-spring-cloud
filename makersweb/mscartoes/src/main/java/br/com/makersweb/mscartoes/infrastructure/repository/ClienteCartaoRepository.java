package br.com.makersweb.mscartoes.infrastructure.repository;

import br.com.makersweb.mscartoes.domain.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author aaristides
 */
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, String> {

    List<ClienteCartao> findByCpf(final String cpf);

}
