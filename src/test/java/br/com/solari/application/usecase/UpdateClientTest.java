package br.com.solari.application.usecase;

import br.com.solari.application.domain.Address;
import br.com.solari.application.domain.Client;
import br.com.solari.application.dto.UpdateClientDto;
import br.com.solari.application.gateway.ClientGateway;
import br.com.solari.application.usecase.exception.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateClientTest {

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private UpdateClient updateClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateClientSuccessfully() {
        // Arrange
        String cpf = "12345678900";
        Client existingClient = buildClient();
        UpdateClientDto updateRequest = buildUpdateClientDto();

        when(clientGateway.findByCpf(cpf)).thenReturn(Optional.of(existingClient));
        when(clientGateway.update(any(Client.class))).thenReturn(existingClient);

        // Act
        Client updatedClient = updateClient.execute(cpf, updateRequest);

        // Assert
        assertNotNull(updatedClient);
        assertEquals(updateRequest.getName(), updatedClient.getName());
        assertEquals(updateRequest.getPhoneNumber(), updatedClient.getPhoneNumber());
        assertEquals(updateRequest.getEmail(), updatedClient.getEmail());
        verify(clientGateway).findByCpf(cpf);
        verify(clientGateway).update(existingClient);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {
        // Arrange
        String cpf = "12345678900";
        UpdateClientDto updateRequest = buildUpdateClientDto();

        when(clientGateway.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        ClientNotFoundException exception = assertThrows(
                ClientNotFoundException.class,
                () -> updateClient.execute(cpf, updateRequest)
        );

        assertEquals("Client with CPF=[" + cpf + "] not found.", exception.getMessage());
        verify(clientGateway).findByCpf(cpf);
        verify(clientGateway, never()).update(any(Client.class));
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

    private UpdateClientDto buildUpdateClientDto() {
        return UpdateClientDto.builder()
                .name("Bruna Atualizada")
                .phoneNumber("+5511988888888")
                .email("bruna.atualizada@example.com")
                .password("NovaSenha@123")
                .address(Address.builder()
                        .street("Rua Nova")
                        .number("456")
                        .city("Rio de Janeiro")
                        .state("RJ")
                        .zipCode("98765-432")
                        .build())
                .build();
    }
}