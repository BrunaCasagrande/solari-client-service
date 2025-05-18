package br.com.solari.infrastructure.gateway;

import br.com.solari.application.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(ClientGatewayImpl.class)
class ClientGatewayImplTest {

    @Autowired
    private ClientGatewayImpl clientGateway;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
/*
    @Test
    void shouldFindClientByCpf() {
        // Arrange
        String cpf = "12345678900";
        String mockResponse = """
            {
                "id": 1,
                "name": "Bruna Casagrande",
                "cpf": "12345678900",
                "email": "bruna@example.com",
                "phoneNumber": "+5511999999999",
                "password": "Senha@123",
                "address": {
                    "street": "Rua das Flores",
                    "number": "123",
                    "city": "São Paulo",
                    "state": "SP",
                    "zipCode": "01234-567"
                }
            }
        """;

        mockServer.expect(requestTo("/external-api/clients?cpf=" + cpf))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        // Act
        Client response = clientGateway.findByCpf(cpf).orElse(null);

        // Assert
        mockServer.verify();
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Bruna Casagrande");
        assertThat(response.getCpf()).isEqualTo("12345678900");
        assertThat(response.getAddress().getCity()).isEqualTo("São Paulo");
    }

 */
}