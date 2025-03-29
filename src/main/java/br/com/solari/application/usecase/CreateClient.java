package br.com.solari.application.usecase;

import br.com.solari.application.domain.Client;
import br.com.solari.application.usecase.exception.ClientAlreadyExistsException;
import br.com.solari.application.gateway.ClientGateway;
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
            request.getPassword(),
            request.getAddress());

    return clientGateway.save(buildDomain);
  }
}
