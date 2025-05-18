package br.com.solari.application.domain;

import br.com.solari.application.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void shouldCreateValidAddress() {
        // Act
        Address address = Address.builder()
                .street("Rua das Flores")
                .number("123")
                .city("São Paulo")
                .state("SP")
                .zipCode("01234-567")
                .build();

        // Assert
        assertNotNull(address);
        assertEquals("Rua das Flores", address.getStreet());
        assertEquals("123", address.getNumber());
        assertEquals("São Paulo", address.getCity());
        assertEquals("SP", address.getState());
        assertEquals("01234-567", address.getZipCode());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsBlank() {
        // Act & Assert
        DomainException exception = assertThrows(DomainException.class, () ->
                Address.createAddress(
                        "",
                        "123",
                        "São Paulo",
                        "SP",
                        "01234-567"
                )
        );

        assertEquals("não deve estar em branco", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeIsBlank() {
        // Act & Assert
        DomainException exception = assertThrows(DomainException.class, () ->
                Address.createAddress(
                        "Rua das Flores",
                        "123",
                        "São Paulo",
                        "SP",
                        ""
                )
        );

        assertEquals("não deve estar em branco", exception.getMessage());
    }
}