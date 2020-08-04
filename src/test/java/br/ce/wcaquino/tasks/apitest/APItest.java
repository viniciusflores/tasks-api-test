package br.ce.wcaquino.tasks.apitest;

import static io.restassured.RestAssured.given;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APItest {
	
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void shouldGetTodoData() {
		given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}
	
	@Test
	public void shouldCreateATodoData() {
		given()
			.body("{\"task\":\"teste API\",\"dueDate\":\"2020-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
		;
	}
	
	@Test
	public void shouldNotCreateATodoData() {
		given()
			.body("{\"task\":\"teste API\",\"dueDate\":\"2010-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message",CoreMatchers.is("Due date must not be in past"))
		;
	}
}
