package day2_WaysToCreatePostRequestBody;

import static io.restassured.RestAssured.given;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class WaysToCreatePostRequestBody
{
	// post request body using hashmap
	// @Test(priority = 1)
	public void testPostUsingHashMap()
	{
		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "france");
		data.put("phone", "123456");

		String courseArr[] =
		{ "c", "c++" };

		data.put("courses", courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("france"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// deleting student record
	// @Test(priority = 2)
	public void testDelete()
	{
		given().when().delete("http://localhost:3000/students/4").then().statusCode(200);
	}

	// post request body using org.json library
	@Test(priority = 1)
	public void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "france");
		data.put("phone", "123456");

		String courseArr[] =
		{ "c", "c++" };

		data.put("courses", courseArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("france"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

}
