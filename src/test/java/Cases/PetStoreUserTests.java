package Cases;

import SwaggerPetStoreAPI.Rest.UserRestClient;
import SwaggerPetStoreAPI.Warpper.UserLib;
import SwaggerPetStoreAPI.dto.ApplicationConstants;
import SwaggerPetStoreAPI.dto.Request.UserRequest;
import SwaggerPetStoreAPI.dto.Response.CreateUserResponse;
import SwaggerPetStoreAPI.dto.Response.GetUserResponse;
import Util.FakerUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class PetStoreUserTests {
  public static final Logger logger = LogManager.getLogger(PetStoreUserTests.class);
  UserLib userLib;
  UserRestClient restClient;
  SoftAssert softAssert;
  ArrayList<Object> userDataList;
  FakerUtil fakerUtil;
  ArrayList<String> userList;
  String firstName;
  String userName;
  Integer id;
  String updatedName;
  UserRequest createUserRequest;

  public static final String UNAVAILABLE_USER = "the&98";

  @BeforeClass
  public void setup() {
    userLib = new UserLib();
    restClient = new UserRestClient();
    fakerUtil = new FakerUtil();
    logger.info("before class end here");
  }

  @BeforeMethod
  public void setup_assert() {
    softAssert = new SoftAssert();
  }

  @Test(priority = 0)
  public void MultipleUserCreationTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    UserRequest userRequest;
    userDataList = new ArrayList<>();
    userList = new ArrayList<>();

    // creating 30 users by adding 30 json object in array of request body
    for (int i = 0; i < 30; i++) {
      String firstName = fakerUtil.getFirstName();
      String userName = fakerUtil.userName();
      Integer id = fakerUtil.getId();
      userList.add(userName);
      userRequest = userLib.createDefaultUserRequest(firstName, userName, id);
      userDataList.add(userRequest);
    }

    CreateUserResponse response = userLib.getCreateUserResponse(userDataList);

    softAssert.assertEquals(
        response.getType(),
        ApplicationConstants.CREATE_USER_RESPONSE_TYPE,
        "type should be unknown but it is not");
    softAssert.assertEquals(
        response.getMessage(),
        ApplicationConstants.CREATE_USER_RESPONSE_OK,
        "message should be ok but it is not");

    softAssert.assertEquals(
        response.getCode(),
        ApplicationConstants.CREATE_USER_STATUS_CODE,
        "status code should be 200 but it is not");

    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {"MultipleUserCreationTest"})
  public void validateMultipleUserGotCreatedTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    ArrayList<String> expectedUserList = new ArrayList<>();
    for (int i = 0; i < userList.size(); i++) {
      GetUserResponse response = userLib.getUserInfo(userList.get(i));
      logger.info("actual userList is" + userList);
      expectedUserList.add(response.getUsername());
      logger.info("expectedUserList userList is" + expectedUserList);
    }

    softAssert.assertEquals(
        expectedUserList, userList, "both the list are not same, multi usr creation is failing");
    softAssert.assertAll();
  }

  @Test(priority = 0)
  public void singleUserCreationTest(Method method) {
    logger.info("test execution has started for method " + method.getName());

    firstName = fakerUtil.getFirstName() + "-";
    userName = fakerUtil.userName() + "#";
    id = fakerUtil.getId();

    ArrayList<Object> list = new ArrayList<>();

    createUserRequest = userLib.createDefaultUserRequest(firstName, userName, id);
    list.add(createUserRequest);
    CreateUserResponse response = userLib.getCreateUserResponse(list);

    softAssert.assertEquals(
        response.getType(),
        ApplicationConstants.CREATE_USER_RESPONSE_TYPE,
        "type should be unknown but it is not");
    softAssert.assertEquals(
        response.getMessage(),
        ApplicationConstants.CREATE_USER_RESPONSE_OK,
        "message should be ok but it is not");

    softAssert.assertEquals(
        response.getCode(),
        ApplicationConstants.CREATE_USER_STATUS_CODE,
        "status code should be 200 but it is not");

    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void getUserInfoTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    GetUserResponse response = userLib.getUserInfo(userName);
    softAssert.assertEquals(response.getFirstName(), firstName, "first name is not matching");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void updateUserInformationTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    updatedName = fakerUtil.getFirstName();
    createUserRequest.setFirstName(updatedName);
    GetUserResponse response = userLib.updateUserInfo(createUserRequest, userName);
    System.out.println("response message is " + response.getMessage());
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void getUpdatedUserInfoTest() {
    GetUserResponse response = userLib.getUserInfo(userName);
    softAssert.assertEquals(response.getFirstName(), updatedName, "first name update failed");
    softAssert.assertAll();
  }

  /**
   * this is negative scenario where we are passing json object in the body instead of array of json
   * object
   *
   * @param method
   */
  @Test
  public void invalidCreateUserPayloadTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    String name = fakerUtil.getFirstName();
    String userName = fakerUtil.userName();
    Integer id = fakerUtil.getId();
    UserRequest userRequest = userLib.createDefaultUserRequest(name, userName, id);

    CreateUserResponse response = userLib.getCreateUserResponse(userRequest);

    softAssert.assertEquals(
        response.getType(),
        ApplicationConstants.CREATE_USER_RESPONSE_TYPE,
        "type should be unknown but it is not");
    softAssert.assertEquals(
        response.getCode(),
        ApplicationConstants.SERVER_ERROR,
        "status code should be 500 but it is not");
    softAssert.assertEquals(
        response.getMessage(),
        ApplicationConstants.CREATE_USER_INVALID_PAYLOAD_RESPONSE_MESSAGE,
        "error message should be something bad happened");

    softAssert.assertAll();
  }

  /**
   * this is negative scenario where we are trying to get user which is not present in db
   *
   * @param method
   */
  @Test
  public void UserNotFoundTest(Method method) {
    logger.info("test execution has started for method " + method.getName());
    GetUserResponse response = userLib.getUserInfo(UNAVAILABLE_USER);
    softAssert.assertEquals(
        response.getMessage(),
        ApplicationConstants.USER_NOT_AVAILABLE_MESSAGE,
        "message should be user not found but it is not");
    softAssert.assertEquals(
        response.getType(),
        ApplicationConstants.USER_NOT_FOUND_RESPONSE_TYPE,
        "type should be error but it is not");
    softAssert.assertAll();
  }
}
