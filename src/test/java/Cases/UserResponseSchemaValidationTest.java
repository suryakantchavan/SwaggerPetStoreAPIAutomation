package Cases;

import SwaggerPetStoreAPI.Rest.UserRestClient;
import SwaggerPetStoreAPI.Warpper.UserLib;
import SwaggerPetStoreAPI.dto.Request.UserRequest;
import SwaggerPetStoreAPI.dto.Response.GetUserResponse;
import Util.ConfigLoader;
import Util.FakerUtil;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UserResponseSchemaValidationTest {
    public static final Logger logger = LogManager.getLogger(UserResponseSchemaValidationTest.class);
    UserLib userLib;
    UserRestClient restClient;
    SoftAssert softAssert;

    FakerUtil fakerUtil;



    public static final String UNAVAILABLE_USER = "the&98";

    @BeforeClass
    public void setup() {
        userLib = new UserLib();
        restClient = new UserRestClient();
        fakerUtil = new FakerUtil();
        softAssert= new SoftAssert();
        logger.info("before class end here");
    }

    @Test
    public void validateUserResponseSchema(Method method)
    {
        logger.info("execution started for method " +method.getName());

        RestAssured.baseURI= ConfigLoader.getInstance().getBaseUrl();
        given()
                .when().get("/v2/user/dsd")

                //verify JSON Schema
                .then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File("src/main/resources/userResponseJsonSchema.json")));
    }
}
