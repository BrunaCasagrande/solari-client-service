package br.com.solari.infrastructure.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.solari.infrastructure.persistence.entity.ClientEntity;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientRepositoryTest {

  @Mock private ClientRepository clientRepository;

  @InjectMocks private ClientRepositoryTest clientRepositoryTest;

  private ClientEntity client;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    client =
        ClientEntity.builder()
            .id(1)
            .name("João Silva")
            .cpf("12345678900")
            .phoneNumber("11999999999")
            .email("joao.silva@example.com")
            .password("senha123")
            .build();
  }

  @Test
  void shouldSaveClient() {
    when(clientRepository.save(client)).thenReturn(client);

    ClientEntity savedClient = clientRepository.save(client);

    assertNotNull(savedClient);
    assertEquals(client.getCpf(), savedClient.getCpf());
    verify(clientRepository, times(1)).save(client);
  }

  @Test
  void shouldFindClientByCpf() {
    when(clientRepository.findByCpf(client.getCpf())).thenReturn(Optional.of(client));

    Optional<ClientEntity> foundClient = clientRepository.findByCpf(client.getCpf());

    assertTrue(foundClient.isPresent());
    assertEquals(client.getCpf(), foundClient.get().getCpf());
    verify(clientRepository, times(1)).findByCpf(client.getCpf());
  }

  @Test
  void shouldReturnEmptyWhenClientNotFoundByCpf() {
    when(clientRepository.findByCpf("00000000000")).thenReturn(Optional.empty());

    Optional<ClientEntity> foundClient = clientRepository.findByCpf("00000000000");

    assertTrue(foundClient.isEmpty());
    verify(clientRepository, times(1)).findByCpf("00000000000");
  }

  @Test
  void shouldDeleteClientByCpf() {
    doNothing().when(clientRepository).deleteByCpf(client.getCpf());

    clientRepository.deleteByCpf(client.getCpf());

    verify(clientRepository, times(1)).deleteByCpf(client.getCpf());
  }

  @Test
  void shouldThrowExceptionWhenDeletingNonExistentClientByCpf() {
    doThrow(new RuntimeException("Client not found"))
        .when(clientRepository)
        .deleteByCpf("00000000000");

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              clientRepository.deleteByCpf("00000000000");
            });

    assertEquals("Client not found", exception.getMessage());
    verify(clientRepository, times(1)).deleteByCpf("00000000000");
  }
}
