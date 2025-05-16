package br.com.solari.application.usecase;

import br.com.solari.application.domain.Client;
import br.com.solari.application.gateway.ClientGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SearchClientTest {

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private SearchClient searchClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnClientWhenFound() {
        // Arrange
        String cpf = "12345678900";
        Client mockClient = mock(Client.class);
        when(clientGateway.findByCpf(cpf)).thenReturn(Optional.of(mockClient));

        // Act
        Optional<Client> result = searchClient.execute(cpf);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockClient, result.get());
        verify(clientGateway).findByCpf(cpf);
    }

    @Test
    void shouldReturnEmptyWhenClientNotFound() {
        // Arrange
        String cpf = "12345678900";
        when(clientGateway.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act
        Optional<Client> result = searchClient.execute(cpf);

        // Assert
        assertTrue(result.isEmpty());
        verify(clientGateway).findByCpf(cpf);
    }
}