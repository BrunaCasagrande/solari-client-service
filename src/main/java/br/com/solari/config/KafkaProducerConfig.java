package br.com.solari.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    // Definindo as configurações do produtor
    Map<String, Object> producerProps = new HashMap<>();
    producerProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092"); // Conexão com o Kafka
    producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // Criando o ProducerFactory com as configurações
    ProducerFactory<String, String> producerFactory =
        new DefaultKafkaProducerFactory<>(producerProps);

    // Retornando o KafkaTemplate configurado
    return new KafkaTemplate<>(producerFactory);
  }
}
