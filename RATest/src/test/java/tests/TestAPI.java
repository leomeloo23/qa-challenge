package tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import tests.UserModel.RequestCreateUser;
import tests.UserModel.ResponseCreateUser;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class TestAPI {
	
	private static final String BASE_URI = "https://reqres.in/api/";
	public String userId;
	@Test
	public void create_user() {
		
		RestAssured.baseURI = BASE_URI;
		
		RequestCreateUser requestCreateUser = new RequestCreateUser();
		requestCreateUser.setName("Leonardo Melo");
		requestCreateUser.setJob("QA");
		
		ResponseCreateUser responseCreateUser =
	            given()
	                .contentType(ContentType.JSON)
	                .body(requestCreateUser)
	            .when()
	                .post("users")
	            .then()
	                .statusCode(201) 
	                .body("name", equalTo("Leonardo Melo")) 
	                .body("job", equalTo("QA")) 
	                .extract()
	                .as(ResponseCreateUser.class); 
	               
			userId = responseCreateUser.getId();
			String createdAt = responseCreateUser.getCreatedAt();

	       
	        System.out.println("ID: " + userId);
	        System.out.println("Criado em: " + responseCreateUser.getCreatedAt());
	        
	        assertNotNull(userId, "O ID do user não pode ser null");
			assertTrue(userId.length() > 0, "O ID do user deve ser maior que 0");
			assertNotNull(createdAt, "Data de criação não pode ser null");
			assertTrue(createdAt.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"), "Data de criação deve seguir o formato padrão");
			
	}
	
	@Test
	public void get_user() {
		
		if (userId == null || userId.isEmpty()) {
			System.out.println("ID do user não está disponivel. Execute primeiro create_user.");
			return;
		}
		
		RestAssured.baseURI = BASE_URI;
		Response response =
			given()
				.pathParam("id", userId)
	        .when()
	        	.get("users/{id}")  
	        .then()
		        .statusCode(404)
		        .body(equalTo("{}"))
		        .extract()
		        .response();

		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		
		long responseTime = response.getTime();
		System.out.println("Tempo de resposta: " + responseTime + " ms");
		
		assertEquals(response.getStatusCode(), 404, "Status code deve ser 404");
		assertEquals(response.getBody().asString(), "{}", "O body deve estar vazio");

	}
}



