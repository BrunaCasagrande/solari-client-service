package br.com.solari.usecase;

import br.com.solari.domain.Client;
import br.com.solari.gateway.ClientGateway;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchClient {

  private final ClientGateway clientGateway;

  public Optional<Client> execute(final String cpf) {
    return clientGateway.findByCpf(cpf);
  }
}
