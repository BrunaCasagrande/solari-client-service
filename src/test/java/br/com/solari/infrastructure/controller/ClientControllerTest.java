package br.com.solari.infrastructure.controller;

import br.com.solari.application.domain.Address;
import br.com.solari.application.domain.Client;
import br.com.solari.application.dto.UpdateClientDto;
import br.com.solari.application.usecase.CreateClient;
import br.com.solari.application.usecase.DeleteClient;
import br.com.solari.application.usecase.SearchClient;
import br.com.solari.application.usecase.UpdateClient;
import br.com.solari.infrastructure.presenter.ClientPresenter;
import br.com.solari.infrastructure.presenter.response.ClientPresenterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CreateClient createClient;

    @Mock
    private DeleteClient deleteClient;

    @Mock
    private SearchClient searchClient;

    @Mock
    private UpdateClient updateClient;

    @Mock
    private ClientPresenter clientPresenter;

    @InjectMocks
    private ClientController clientController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper(); // Inicializa o ObjectMapper
    }

    @Test
    void shouldCreateClient() throws Exception {
        // Arrange
        Client request = Client.builder()
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

        Client createdClient = request; // Simula o cliente criado
        ClientPresenterResponse response = new ClientPresenterResponse(
                1,
                "Bruna Casagrande",
                "12345678900",
                "bruna@example.com",
                "+5511999999999",
                new Address(1, "Rua das Flores", "123", "São Paulo", "SP", "01234-567")
        );

        when(createClient.execute(any(Client.class))).thenReturn(createdClient);
        when(clientPresenter.parseToResponse(createdClient)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/solari/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    @Test
    void shouldFindClientByCpf() throws Exception {
        // Arrange
        String cpf = "12345678900";
        Client client = new Client();
        ClientPresenterResponse response = new ClientPresenterResponse(
                1,
                "Bruna Casagrande",
                "12345678900",
                "bruna@example.com",
                "+5511999999999",
                new Address(1, "Rua das Flores", "123", "São Paulo", "SP", "01234-567")
        );

        when(searchClient.execute(cpf)).thenReturn(Optional.of(client));
        when(clientPresenter.parseToResponse(client)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/solari/v1/clients/{cpf}", cpf))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenClientDoesNotExist() throws Exception {
        // Arrange
        String cpf = "12345678900";

        when(searchClient.execute(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/solari/v1/clients/{cpf}", cpf))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateClient() throws Exception {
        // Arrange
        String cpf = "12345678900";
        UpdateClientDto request = new UpdateClientDto();
        Client updatedClient = new Client();
        ClientPresenterResponse response = new ClientPresenterResponse(
                1,
                "Bruna Casagrande",
                "12345678900",
                "bruna@example.com",
                "+5511999999999",
                new Address(1, "Rua das Flores", "123", "São Paulo", "SP", "01234-567")
        );

        when(updateClient.execute(eq(cpf), any(UpdateClientDto.class))).thenReturn(updatedClient);
        when(clientPresenter.parseToResponse(updatedClient)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/solari/v1/clients/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteClient() throws Exception {
        // Arrange
        String cpf = "12345678900";

        doNothing().when(deleteClient).execute(cpf);

        // Act & Assert
        mockMvc.perform(delete("/solari/v1/clients/{cpf}", cpf))
                .andExpect(status().isNoContent());
    }
}