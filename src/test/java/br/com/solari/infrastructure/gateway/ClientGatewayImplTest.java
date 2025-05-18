package br.com.solari.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.solari.application.domain.Address;
import br.com.solari.application.domain.Client;
import br.com.solari.infrastructure.persistence.entity.AddressEntity;
import br.com.solari.infrastructure.persistence.entity.ClientEntity;
import br.com.solari.infrastructure.persistence.repository.ClientRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientGatewayImplTest {

  @Mock private ClientRepository clientRepository;

  @InjectMocks private ClientGatewayImpl clientGateway;

  private Client client;
  private ClientEntity clientEntity;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    Address address =
        Address.builder()
            .street("Rua das Flores")
            .number("123")
            .city("São Paulo")
            .state("SP")
            .zipCode("01234-567")
            .build();

    client =
        Client.builder()
            .id(1)
            .name("João Silva")
            .cpf("12345678900")
            .phoneNumber("11999999999")
            .email("joao.silva@example.com")
            .password("senha123")
            .address(address)
            .build();

    AddressEntity addressEntity =
        AddressEntity.builder()
            .street("Rua das Flores")
            .number("123")
            .city("São Paulo")
            .state("SP")
            .zipCode("01234-567")
            .build();

    clientEntity =
        ClientEntity.builder()
            .id(1)
            .name("João Silva")
            .cpf("12345678900")
            .phoneNumber("11999999999")
            .email("joao.silva@example.com")
            .password("senha123")
            .address(addressEntity)
            .build();
  }

  @Test
  void shouldSaveClient() {
    when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

    Client savedClient = clientGateway.save(client);

    assertNotNull(savedClient);
    assertEquals(client.getCpf(), savedClient.getCpf());
    verify(clientRepository, times(1)).save(any(ClientEntity.class));
  }

  @Test
  void shouldFindClientByCpf() {
    when(clientRepository.findByCpf(client.getCpf())).thenReturn(Optional.of(clientEntity));

    Optional<Client> foundClient = clientGateway.findByCpf(client.getCpf());

    assertTrue(foundClient.isPresent());
    assertEquals(client.getCpf(), foundClient.get().getCpf());
    verify(clientRepository, times(1)).findByCpf(client.getCpf());
  }

  @Test
  void shouldUpdateClient() {
    when(clientRepository.findByCpf(client.getCpf())).thenReturn(Optional.of(clientEntity));
    when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

    Client updatedClient = clientGateway.update(client);

    assertNotNull(updatedClient);
    assertEquals(client.getCpf(), updatedClient.getCpf());
    verify(clientRepository, times(1)).findByCpf(client.getCpf());
    verify(clientRepository, times(1)).save(any(ClientEntity.class));
  }

  @Test
  void shouldDeleteClientByCpf() {
    doNothing().when(clientRepository).deleteByCpf(client.getCpf());

    clientGateway.deleteByCpf(client.getCpf());

    verify(clientRepository, times(1)).deleteByCpf(client.getCpf());
  }
}
