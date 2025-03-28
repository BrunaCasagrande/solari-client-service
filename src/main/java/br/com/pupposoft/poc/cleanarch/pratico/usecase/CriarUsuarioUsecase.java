package br.com.pupposoft.poc.cleanarch.pratico.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.cleanarch.pratico.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.pratico.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.pratico.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.pratico.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.pratico.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.pratico.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriarUsuarioUsecase {

	private final UsuarioGateway usuarioGateway;

	public Long criar(Usuario usuario) {

		//TODO: as regras tendem a crescer e por isso deveriam estar fora daqui.
		Optional<Usuario> usuarioOp = usuarioGateway.obterPorCpf(usuario.getCpf());
		if(usuarioOp.isPresent()) {
			log.warn("Usuário ja existe com cpf informado. {}", usuario.getCpf());
			throw new UsuarioExistenteException();
		}

		if(usuario.isMenorIdade()) {
			log.warn("Usuário menor de idade. idade={}", usuario.getIdade());
			throw new UsuarioMenorIdadeException();
		}

		return usuarioGateway.criar(usuario);
	}
}
