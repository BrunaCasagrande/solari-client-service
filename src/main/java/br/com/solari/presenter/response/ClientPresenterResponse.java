package br.com.solari.presenter.response;

import lombok.Builder;

@Builder
public record ClientPresenterResponse(int id, String name, String cpf, String phoneNumber, String email) {
}
