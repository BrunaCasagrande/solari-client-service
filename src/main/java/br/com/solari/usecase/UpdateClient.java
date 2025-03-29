package br.com.solari.usecase;

import br.com.solari.domain.Client;
import br.com.solari.dto.UpdateClientDto;
import br.com.solari.exception.ClientNotFoundException;
import br.com.solari.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateClient {

  private final ClientGateway clientGateway;

  public Client execute(final String cpf, final UpdateClientDto request) {
    final Client existingUser =
        clientGateway.findByCpf(cpf).orElseThrow(() -> new ClientNotFoundException(cpf));

    existingUser.setName(request.getName());
    existingUser.setPhoneNumber(request.getPhoneNumber());
    existingUser.setEmail(request.getEmail());
    existingUser.setPassword(request.getPassword());

    return clientGateway.update(existingUser);
  }
}
