package Api;

import io.restassured.response.Response;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    // Регистрация пользователя
    public static Response registerUser(String email, String password, String name) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\", \"name\":\"" + name + "\"}")
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .extract().response();
    }

    // Вход пользователя
    public static Response loginUser(String email, String password) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .extract().response();
    }


    // Метод для извлечения токена из ответа
    public static String getAccessToken(Response response) {
        return response.path("accessToken");
    }

    // Новый метод: регистрировать и логинить пользователя, возвращая все нужные данные
    public static UserCredentials registerAndLogin(String email, String password, String name) {

        registerUser(email, password, name);

        Response loginResponse = loginUser(email, password);
        String token = "Bearer " + getAccessToken(loginResponse);

        return new UserCredentials(email, password, token);
    }

    // Вспомогательный класс для хранения данных
    public static class UserCredentials {
        public String email;
        public String password;
        public String token;

        public UserCredentials(String email, String password, String token) {
            this.email = email;
            this.password = password;
            this.token = token;
        }
    }
    public static Response deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}