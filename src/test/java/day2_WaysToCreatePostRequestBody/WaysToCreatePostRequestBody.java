package day2_WaysToCreatePostRequestBody;

import static io.restassured.RestAssured.given;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
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
	// @Test(priority = 2)
	public void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();
		data.put("name", "Vaibhav");
		data.put("location", "france");
		data.put("phone", "123456");

		String courseArr[] =
		{ "c", "c++" };

		data.put("courses", courseArr);

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("Vaibhav")).body("location", equalTo("france"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// 3.post request body using POJO class
	// @Test
	public void testPostUsingPOJO()
	{
		POJO_PostRequest data = new POJO_PostRequest();
		data.setName("Vaibhav");
		data.setLocation("france");
		data.setPhone("123456");
		String coursesArr[] =
		{ "c", "c++" };
		data.setCourses(coursesArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Vaibhav")).body("location", equalTo("france"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

	// 4.post request body using external json file
	@Test
	public void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		// f represents the file
		File f = new File(".\\Body.json");

		// to read the data from the file
		FileReader fr = new FileReader(f);

		JSONTokener jt = new JSONTokener(fr);

		JSONObject data = new JSONObject(jt);
		// if data is available in json object, we have to convert to string format
		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("Vaibhav")).body("location", equalTo("france"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

	}

}
