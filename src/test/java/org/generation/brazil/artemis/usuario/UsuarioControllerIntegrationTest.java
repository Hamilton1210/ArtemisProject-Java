package org.generation.brazil.artemis.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.generation.brazil.artemis.ArtemisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtemisApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  /**
   * Monta a URL para a chamada de teste da API
   * @param path - caminho da API
   * @return String
   */
  private String getRootUrl(String path) {
    return "http://localhost:" + port + "/api/v1" + path;
  }

  @Test
  public void testaCriacaoDeUmNovoUsuario() {

    // "Chamada da API"
    ResponseEntity<Usuario> postResponse =
        testRestTemplate.postForEntity(
            getRootUrl("/usuarios"),
            UsuarioMock.getUsuarioMock(),
            Usuario.class);

    assertNotNull(postResponse);
    assertEquals(201,
        postResponse.getStatusCodeValue());
  }

}