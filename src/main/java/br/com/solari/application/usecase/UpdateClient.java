package br.com.solari.application.usecase;

import br.com.solari.application.domain.Client;
import br.com.solari.application.dto.UpdateClientDto;
import br.com.solari.application.usecase.exception.ClientNotFoundException;
import br.com.solari.application.gateway.ClientGateway;
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
    existingUser.setAddress(request.getAddress());

    return clientGateway.update(existingUser);
  }
}
