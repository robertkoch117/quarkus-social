package io.github.robertkoch.quarkussocial.rest.resource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.robertkoch.quarkussocial.rest.dto.CreateUserRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@QuarkusTest
class UserResourceTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("should create an user successfully")
	public void createUserTest() {
		CreateUserRequest user = new CreateUserRequest();
		user.setName("Robert");
		user.setAge(30);
		
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(user)
				.when()
					.post("/users")
				.then()
					.extract().response();
		
		assertEquals(201, response.statusCode());
		assertNotNull(response.jsonPath().getString("id"));
	}
}
