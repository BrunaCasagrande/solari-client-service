package br.com.solari.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientDto {

  private String name;

  private String phoneNumber;

  private String email;

  private String password;
}
