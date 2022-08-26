package io.github.robertkoch.quarkussocial.rest.resource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.robertkoch.quarkussocial.rest.dto.CreateUserRequest;
import io.github.robertkoch.quarkussocial.rest.dto.exception.ResponseError;
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
	
	@Test
	@DisplayName("Should return error when json is not valid")
	public void createUserValidationErrorTest() {
		CreateUserRequest user = new CreateUserRequest();
		user.setAge(null);
		user.setName(null);
		
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.body(user)
				.when()
					.post("/users")
				.then()
					.extract().response();
		
		assertEquals(ResponseError.UNPROCESSABLE_ENTITY_STATUS, response.statusCode());
		assertEquals("Validation Error", response.jsonPath().getString("message"));
		
		List<Map<String, String>> errors = response.jsonPath().getList("errors");
		assertNotNull(errors.get(0).get("message"));
		assertNotNull(errors.get(1).get("message"));
//		assertEquals("Age is Required", errors.get(0).get("message"));
//		assertEquals("Name is Required", errors.get(1).get("message"));
	}
}
