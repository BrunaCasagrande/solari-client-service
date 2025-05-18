package br.com.solari.infrastructure.presenter;

import static org.junit.jupiter.api.Assertions.*;

import br.com.solari.application.domain.Address;
import br.com.solari.application.domain.Client;
import br.com.solari.infrastructure.presenter.response.ClientPresenterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientPresenterTest {

  private ClientPresenter clientPresenter;

  private Client client;

  @BeforeEach
  void setUp() {
    clientPresenter = new ClientPresenter();

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
            .address(address)
            .build();
  }

  @Test
  void shouldParseClientToResponse() {
    ClientPresenterResponse response = clientPresenter.parseToResponse(client);

    assertNotNull(response);
    assertEquals(client.getId(), response.id());
    assertEquals(client.getName(), response.name());
    assertEquals(client.getCpf(), response.cpf());
    assertEquals(client.getPhoneNumber(), response.phoneNumber());
    assertEquals(client.getEmail(), response.email());
    assertEquals(client.getAddress(), response.address());
  }
}
