package utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.simple.JSONObject;


import static io.restassured.RestAssured.given;


public class RestHelper {

	public Response doPostRequest(String endpoint, JSONObject header, JSONObject body) {
		RestAssured.defaultParser = Parser.JSON;
		return given().urlEncodingEnabled(false).log().uri().log().method().log().params()
				.log().body().contentType(ContentType.JSON).accept(ContentType.ANY).with().body(body).headers(header)
				.log().headers().relaxedHTTPSValidation().when().post(endpoint).then().log().all().extract().response();
	}

}
