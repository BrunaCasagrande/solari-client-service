package br.com.solari.application.domain;

import br.com.solari.application.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void shouldCreateValidClient() {
        // Arrange
        Address address = Address.builder()
                .street("Rua das Flores")
                .number("123")
                .city("São Paulo")
                .state("SP")
                .zipCode("01234-567")
                .build();

        // Act
        Client client = Client.createClient(
                "Bruna Casagrande",
                "12345678900",
                "+5511999999999",
                "bruna@example.com",
                "Senha@123",
                address
        );

        // Assert
        assertNotNull(client);
        assertEquals("Bruna Casagrande", client.getName());
        assertEquals("12345678900", client.getCpf());
        assertEquals("+5511999999999", client.getPhoneNumber());
        assertEquals("bruna@example.com", client.getEmail());
        assertEquals("Senha@123", client.getPassword());
        assertEquals(address, client.getAddress());
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalid() {
        // Arrange
        Address address = Address.builder()
                .street("Rua das Flores")
                .number("123")
                .city("São Paulo")
                .state("SP")
                .zipCode("01234-567")
                .build();

        // Act & Assert
        DomainException exception = assertThrows(DomainException.class, () ->
                Client.createClient(
                        "Bruna Casagrande",
                        "123", // CPF inválido
                        "+5511999999999",
                        "bruna@example.com",
                        "Senha@123",
                        address
                )
        );

        assertEquals("CPF must be 11 digits", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        // Arrange
        Address address = Address.builder()
                .street("Rua das Flores")
                .number("123")
                .city("São Paulo")
                .state("SP")
                .zipCode("01234-567")
                .build();

        // Act & Assert
        DomainException exception = assertThrows(DomainException.class, () ->
                Client.createClient(
                        "Bruna Casagrande",
                        "12345678900",
                        "+5511999999999",
                        "invalid-email", // E-mail inválido
                        "Senha@123",
                        address
                )
        );

        assertEquals("E-mail should be valid", exception.getMessage());
    }
}