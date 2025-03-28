package br.com.pupposoft.poc.cleanarch.pratico.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Usuario {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	
	public Long getIdade() {
		return dataNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
	}
	
	public boolean isMenorIdade() {
		return getIdade() < 18;
	}
}
