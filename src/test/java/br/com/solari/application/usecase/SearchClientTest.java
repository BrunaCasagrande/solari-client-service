package br.com.solari.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import br.com.solari.application.domain.Client;
import br.com.solari.application.gateway.ClientGateway;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SearchClientTest {

  @Mock private ClientGateway clientGateway;

  @InjectMocks private SearchClient searchClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnClientWhenFound() {
    String cpf = "12345678900";
    Client mockClient = mock(Client.class);
    when(clientGateway.findByCpf(cpf)).thenReturn(Optional.of(mockClient));

    Optional<Client> result = searchClient.execute(cpf);

    assertTrue(result.isPresent());
    assertEquals(mockClient, result.get());
    verify(clientGateway).findByCpf(cpf);
  }

  @Test
  void shouldReturnEmptyWhenClientNotFound() {
    String cpf = "12345678900";
    when(clientGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    Optional<Client> result = searchClient.execute(cpf);

    assertTrue(result.isEmpty());
    verify(clientGateway).findByCpf(cpf);
  }
}
