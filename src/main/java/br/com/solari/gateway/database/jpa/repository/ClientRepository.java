package br.com.solari.gateway.database.jpa.repository;

import br.com.solari.gateway.database.jpa.entity.ClientEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

	Optional<ClientEntity> findByCpf(final String cpf);

	void deleteByCpf(String cpf);
}
