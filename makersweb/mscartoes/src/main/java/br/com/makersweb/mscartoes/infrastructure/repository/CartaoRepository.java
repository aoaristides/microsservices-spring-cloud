package br.com.makersweb.mscartoes.infrastructure.repository;

import br.com.makersweb.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author aaristides
 */
public interface CartaoRepository extends JpaRepository<Cartao, String> {

    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);

}
