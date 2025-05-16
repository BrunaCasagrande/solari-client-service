package br.com.solari.application.usecase;

import br.com.solari.application.domain.Client;
import br.com.solari.application.domain.Address;
import br.com.solari.application.gateway.ClientGateway;
import br.com.solari.application.usecase.exception.ClientAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateClientTest {

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private CreateClient createClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateClientSuccessfully() {
        // Arrange
        Client request = buildClient();
        when(clientGateway.findByCpf(request.getCpf())).thenReturn(Optional.empty());
        when(clientGateway.save(any(Client.class))).thenReturn(request);

        // Act
        Client result = createClient.execute(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getCpf(), result.getCpf());
        verify(clientGateway).findByCpf(request.getCpf());
        verify(clientGateway).save(any(Client.class));
    }

    @Test
    void shouldThrowExceptionWhenClientAlreadyExists() {
        // Arrange
        Client request = buildClient();
        when(clientGateway.findByCpf(request.getCpf())).thenReturn(Optional.of(request));

        // Act & Assert
        ClientAlreadyExistsException exception = assertThrows(
                ClientAlreadyExistsException.class,
                () -> createClient.execute(request)
        );

        assertEquals("Client with cpf=[" + request.getCpf() + "] already exists.", exception.getMessage());
        verify(clientGateway).findByCpf(request.getCpf());
        verify(clientGateway, never()).save(any(Client.class));
    }

    private Client buildClient() {
        return Client.builder()
                .name("Bruna Casagrande")
                .cpf("12345678900")
                .phoneNumber("+5511999999999")
                .email("bruna@example.com")
                .password("Senha@123")
                .address(Address.builder()
                        .street("Rua das Flores")
                        .number("123")
                        .city("São Paulo")
                        .state("SP")
                        .zipCode("01234-567")
                        .build())
                .build();
    }
}