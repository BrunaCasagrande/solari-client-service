package br.com.pupposoft.poc.cleanarch.pratico.producer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Método para enviar pedido para a fila Kafka
    public void sendPedido(String pedido) {
        kafkaTemplate.send("novo-pedido-queue", pedido);
    }
}