package br.com.robertobutzkejunior.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import br.com.robertobutzkejunior.todolist.entity.Todo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl() {
		return "http://localhost:" + port + "/todos";
	}

	@Test
	void testCreateTodoSuccess() throws IOException, InterruptedException {
		Todo todo = new Todo("todo 1", "descricao 1", false, 1);

		String json = "{\"nome\":\"todo 1\",\"descricao\":\"descricao 1\",\"realizado\":false,\"prioridade\":1}";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl()))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		assertEquals(201, response.statusCode(), "Status deve ser 201 Created");
		String body = response.body();
		assertNotNull(body);
		assertTrue(body.contains("todo 1"), "Deve conter nome 'todo 1'");
		assertTrue(body.contains("descricao 1"), "Deve conter descricao 'descricao 1'");
		assertTrue(body.contains("false"), "Deve conter realizado false");
		assertTrue(body.contains("1"), "Deve conter prioridade 1");
	}

}
