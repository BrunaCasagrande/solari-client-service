package br.com.solari.usecase;

import br.com.solari.domain.Client;
import br.com.solari.exception.ClientAlreadyExistsException;
import br.com.solari.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateClient {

  private final ClientGateway clientGateway;

  public Client execute(final Client request) {
    final var user = clientGateway.findByCpf(request.getCpf());

    if (user.isPresent()) {
      throw new ClientAlreadyExistsException(request.getCpf());
    }

    final var buildDomain =
        Client.createClient(
            request.getName(),
            request.getCpf(),
            request.getPhoneNumber(),
            request.getEmail(),
            request.getPassword());

    return clientGateway.save(buildDomain);
  }
}
