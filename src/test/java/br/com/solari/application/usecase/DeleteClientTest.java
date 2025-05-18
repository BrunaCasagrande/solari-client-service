package br.com.solari.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.solari.application.domain.Client;
import br.com.solari.application.gateway.ClientGateway;
import br.com.solari.application.usecase.exception.ClientNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteClientTest {

  @Mock private ClientGateway clientGateway;

  @InjectMocks private DeleteClient deleteClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDeleteClientSuccessfully() {
    String cpf = "12345678900";
    Client mockClient = mock(Client.class);
    when(clientGateway.findByCpf(cpf)).thenReturn(Optional.of(mockClient));

    deleteClient.execute(cpf);

    verify(clientGateway).findByCpf(cpf);
    verify(clientGateway).deleteByCpf(cpf);
  }

  @Test
  void shouldThrowExceptionWhenClientNotFound() {
    String cpf = "12345678900";
    when(clientGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    ClientNotFoundException exception =
        assertThrows(ClientNotFoundException.class, () -> deleteClient.execute(cpf));

    assertEquals("Client with CPF=[" + cpf + "] not found.", exception.getMessage());
    verify(clientGateway).findByCpf(cpf);
    verify(clientGateway, never()).deleteByCpf(cpf);
  }
}
