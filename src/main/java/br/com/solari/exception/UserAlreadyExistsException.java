package br.com.solari.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends BusinessException {

  private static final String ERROR_CODE = "already_exists";
  private static final String MESSAGE = "User with cpf=[%s] already exists.";

  public UserAlreadyExistsException(final String cpf) {
    super(format(MESSAGE, cpf), ERROR_CODE);
  }
}
