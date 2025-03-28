package br.com.solari.gateway.database.jpa;

import static java.lang.String.format;

import br.com.solari.domain.Client;
import br.com.solari.exception.GatewayException;
import br.com.solari.gateway.ClientGateway;
import br.com.solari.gateway.database.jpa.entity.ClientEntity;
import br.com.solari.gateway.database.jpa.repository.ClientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientGatewayImpl implements ClientGateway {

  private final ClientRepository clientRepository;
  private static final String FIND_ERROR_MESSAGE = "User with CPF=[%s] not found.";

  @Override
  public Client save(final Client client) {
    final var entity =
        ClientEntity.builder()
            .name(client.getName())
            .cpf(client.getCpf())
            .phoneNumber(client.getPhoneNumber())
            .email(client.getEmail())
            .password(client.getPassword())
            .build();

    final var saved = clientRepository.save(entity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Client> findByCpf(final String cpf) {
    final var entity = clientRepository.findByCpf(cpf);

    return entity.map(this::toResponse);
  }

  @Override
  public Client update(final Client user) {
    try {
      final var entity =
          clientRepository
              .findByCpf(user.getCpf())
              .orElseThrow(() -> new GatewayException(format(FIND_ERROR_MESSAGE, user.getCpf())));

      entity.setName(user.getName());
      entity.setPhoneNumber(user.getPhoneNumber());
      entity.setEmail(user.getEmail());
      entity.setPassword(user.getPassword());

      final var updatedEntity = clientRepository.save(entity);

      return this.toResponse(updatedEntity);
    } catch (IllegalArgumentException e) {
      throw new GatewayException(format(FIND_ERROR_MESSAGE, user.getCpf()));
    }
  }

  @Override
  public void deleteByCpf(final String cpf) {
    clientRepository.deleteByCpf(cpf);
  }

  private Client toResponse(final ClientEntity entity) {
    return Client.builder()
        .id(entity.getId())
        .name(entity.getName())
        .cpf(entity.getCpf())
        .phoneNumber(entity.getPhoneNumber())
        .email(entity.getEmail())
        .build();
  }
}
