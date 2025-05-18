package br.com.solari.application.domain;

import br.com.solari.application.domain.exception.DomainException;
import br.com.solari.application.domain.exception.ErrorDetail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  private Integer id;

  @NotBlank private String street;
  @NotBlank private String number;
  @NotBlank private String city;
  @NotBlank private String state;
  @NotBlank private String zipCode;

  public static Address createAddress(
      String street, String number, String city, String state, String zipCode) {
    Address address =
        Address.builder()
            .street(street)
            .number(number)
            .city(city)
            .state(state)
            .zipCode(zipCode)
            .build();

    validate(address);

    return address;
  }

  private static void validate(Address address) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Address>> violations = validator.validate(address);

    if (!violations.isEmpty()) {
      List<ErrorDetail> errors =
          violations.stream()
              .map(
                  violation ->
                      new ErrorDetail(
                          violation.getPropertyPath().toString(),
                          violation.getMessage(),
                          violation.getInvalidValue()))
              .toList();

      String firstErrorMessage = errors.get(0).errorMessage();

      throw new DomainException(firstErrorMessage, "domain_exception", errors);
    }
  }
}
