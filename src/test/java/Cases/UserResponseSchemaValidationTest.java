package Cases;

import SwaggerPetStoreAPI.Rest.UserRestClient;
import SwaggerPetStoreAPI.Warpper.UserLib;
import SwaggerPetStoreAPI.dto.ApplicationConstants;
import Util.ConfigLoader;
import Util.FakerUtil;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import java.io.File;
import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;

public class UserResponseSchemaValidationTest {
  public static final Logger logger = LogManager.getLogger(UserResponseSchemaValidationTest.class);

  @Test
  public void validateUserResponseSchema(Method method) {
    logger.info("execution started for method " + method.getName());
    FakerUtil fakerUtil= new FakerUtil();
    RestAssured.baseURI = ConfigLoader.getInstance().getBaseUrl();
    given()
        .when()
        .basePath(ApplicationConstants.BASE_PATH)
        .get(String.format(ApplicationConstants.GET_OR_UPDATE_USER_PATH, fakerUtil.userName()))
        // verify JSON Schema
        .then()
        .assertThat()
        .body(
            JsonSchemaValidator.matchesJsonSchema(
                new File("src/main/resources/userResponseJsonSchema.json")));
  }
}
