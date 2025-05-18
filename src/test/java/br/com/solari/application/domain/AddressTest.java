package br.com.solari.application.domain;

import static org.junit.jupiter.api.Assertions.*;

import br.com.solari.application.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

class AddressTest {

  @Test
  void shouldCreateValidAddress() {
    Address address =
        Address.builder()
            .street("Rua das Flores")
            .number("123")
            .city("São Paulo")
            .state("SP")
            .zipCode("01234-567")
            .build();

    assertNotNull(address);
    assertEquals("Rua das Flores", address.getStreet());
    assertEquals("123", address.getNumber());
    assertEquals("São Paulo", address.getCity());
    assertEquals("SP", address.getState());
    assertEquals("01234-567", address.getZipCode());
  }

  @Test
  void shouldThrowExceptionWhenStreetIsBlank() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Address.createAddress("", "123", "São Paulo", "SP", "01234-567"));

    assertEquals("não deve estar em branco", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenZipCodeIsBlank() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Address.createAddress("Rua das Flores", "123", "São Paulo", "SP", ""));

    assertEquals("não deve estar em branco", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenCityIsNull() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Address.createAddress("Rua das Flores", "123", null, "SP", "01234-567"));

    assertEquals("não deve estar em branco", exception.getMessage());
  }
}
