package br.com.solari.domain;

import br.com.solari.domain.exception.DomainException;
import br.com.solari.domain.exception.ErrorDetail;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "CPF is required")
  @Pattern(regexp = "\\d{11}", message = "CPF must be 11 digits")
  private String cpf;

  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
  private String phoneNumber;

  @NotBlank(message = "E-mail is required")
  @Size(max = 255, message = "E-mail length must be less than 255 characters")
  @Email(message = "E-mail should be valid")
  private String email;

  @NotBlank(message = "Password is required")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
      message =
          "The password must be between 8 and 16 characters long, including letters, numbers and at least one special character (@$!%*?&).")
  private String password;

  public static Client createClient(
      final String name,
      final String cpf,
      final String phoneNumber,
      final String email,
      final String password) {

    final var client =
        Client.builder()
            .name(name)
            .cpf(cpf)
            .phoneNumber(phoneNumber)
            .email(email)
            .password(password)
            .build();

    validate(client);

    return client;
  }

  private static void validate(final Client user) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<Client>> violations = validator.validate(user);

    if (!violations.isEmpty()) {
      final List<ErrorDetail> errors =
          violations.stream()
              .map(
                  violation ->
                      new ErrorDetail(
                          violation.getPropertyPath().toString(),
                          violation.getMessage(),
                          violation.getInvalidValue()))
              .toList();

      final String firstErrorMessage = errors.get(0).errorMessage();

      throw new DomainException(firstErrorMessage, "domain_exception", errors);
    }
  }
}
