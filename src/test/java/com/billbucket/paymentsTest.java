package com.billbucket;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class paymentsTest {

    @Test
    @TestSecurity(authorizationEnabled = false)
    public void testHelloEndpoint() {
        given()
          .when().get("/payment/permit-all")
          .then()
             .statusCode(200)
             .body(is("Hello permit all"));
    }

    @Test
    public void testWithoutScope() {
        given()
                .when().get("/payment/permit-scope")
                .then()
                .statusCode(401);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"lo"})
    public void testWithScope() {
        given()
                .when().get("/payment/permit-scope")
                .then()
                .statusCode(200);
    }

}