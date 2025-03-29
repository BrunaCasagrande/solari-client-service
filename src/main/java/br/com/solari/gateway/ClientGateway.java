package br.com.solari.gateway;

import br.com.solari.domain.Client;

import java.util.Optional;

public interface ClientGateway {

	Client save(Client client);

	Optional<Client> findByCpf(String cpf);

	Client update(Client user);

	void deleteByCpf(String cpf);
}
