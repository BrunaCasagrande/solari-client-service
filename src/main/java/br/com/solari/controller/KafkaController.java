package br.com.solari.controller;

import br.com.solari.producer.PedidoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

  @Autowired private PedidoProducer pedidoProducer;

  @PostMapping("/pedido")
  public String sendPedido(@RequestBody String pedido) {
    pedidoProducer.sendPedido(pedido);
    return "Pedido enviado para a fila com sucesso!";
  }
}
