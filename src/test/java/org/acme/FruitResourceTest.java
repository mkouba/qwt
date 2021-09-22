package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testFruitEndpoint() {
        given().body("{\"name\":\"apple\"}").header("Content-type", MediaType.APPLICATION_JSON)
                .when().post("/fruits")
                .then()
                .statusCode(200);
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body(
                        containsString("apple"));
    }

}