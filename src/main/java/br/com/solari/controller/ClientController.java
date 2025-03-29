package br.com.solari.controller;

import br.com.solari.usecase.CreateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/solari/v1/clients")
@RequiredArgsConstructor
public class ClientController {

  private CreateClient createClient;
}
