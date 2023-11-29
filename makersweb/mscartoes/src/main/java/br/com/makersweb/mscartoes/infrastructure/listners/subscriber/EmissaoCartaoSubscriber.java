package br.com.makersweb.mscartoes.infrastructure.listners.subscriber;

import br.com.makersweb.mscartoes.domain.ClienteCartao;
import br.com.makersweb.mscartoes.domain.ClienteCartaoID;
import br.com.makersweb.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import br.com.makersweb.mscartoes.infrastructure.repository.CartaoRepository;
import br.com.makersweb.mscartoes.infrastructure.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author aaristides
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        log.info("Payload: {}", payload);

        try {
            var mapper = new ObjectMapper();
            var dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            var cartao = cartaoRepository.findById(dados.getCartao());
            if (cartao.isPresent()) {
                var clienteCartao = new ClienteCartao();
                clienteCartao.setId(ClienteCartaoID.unique().getValue());
                clienteCartao.setCartao(cartao.get());
                clienteCartao.setCpf(dados.getCpf());
                clienteCartao.setProtocolo(dados.getProtocolo());
                clienteCartao.setLimite(dados.getLimiteLiberado());
                clienteCartaoRepository.save(clienteCartao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
