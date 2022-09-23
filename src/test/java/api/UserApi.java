package api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApi {
    public String accessToken;

    public Response loginUser(String email, String password) {
        String json = String.format("{\"email\": \"%s\",\"password\": \"%s\"}", email, password);

        return given()
                .header("content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/auth/login");
    }

    public Response loginUserSuccessfully() {
        return loginUser(data_for_tests.User.EMAIL, data_for_tests.User.PASSWORD);
    }

    public void saveAccessToken(Response response) {
        this.accessToken = response.then().extract().path("accessToken");
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public Response deleteUser() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        headers.put("Authorization", accessToken);

        return given()
                .headers(headers)
                .and()
                .body("{}")
                .when()
                .delete("/api/auth/user");
    }

    public Response createUser(String email, String password, String name) {
        String json = String.format(
                "{\"email\": \"%s\",\"password\": \"%s\",\"name\": \"%s\"}",
                email, password, name
        );

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/auth/register");
    }
}
