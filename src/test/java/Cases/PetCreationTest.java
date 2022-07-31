package Cases;

import SwaggerPetStoreAPI.Warpper.PetLib;
import SwaggerPetStoreAPI.dto.ApplicationConstants;
import SwaggerPetStoreAPI.dto.Request.CreatePetRequest;

import SwaggerPetStoreAPI.dto.Response.GetPetStatusResponse;
import SwaggerPetStoreAPI.dto.Response.PetCreationResponse;

import Util.FakerUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class PetCreationTest {

  PetLib petLib;
  FakerUtil fakerUtil;
  SoftAssert softAssert;
  String petName;

  public static final Logger logger = LogManager.getLogger(PetCreationTest.class);

  @BeforeClass
  public void setup() {
    petLib = new PetLib();
    fakerUtil = new FakerUtil();
  }

  @BeforeMethod
  public void setup_assert() {

    softAssert = new SoftAssert();
  }

  @Test(priority = 0)
  public void testPetCreation(Method method) {

    logger.info("test execution has started for method " + method.getName());
    String tagName = fakerUtil.getTagName();
    Integer tagId = fakerUtil.getTagId();
    String categoryName = fakerUtil.getCategoryName();
    Integer categoryId = fakerUtil.getCategoryId();
    String url = fakerUtil.getUrl();
    petName = fakerUtil.getPetName();
    String petStatus = ApplicationConstants.petStatus.SOLD.getPetStatus();
    Integer petId = fakerUtil.getPetId();

    CreatePetRequest createPetRequest =
        petLib.addNewPetRequest(
            tagName, tagId, categoryName, categoryId, url, petName, petStatus, petId);
    System.out.println(createPetRequest);
    PetCreationResponse petCreationResponse = petLib.getPetCreationResponse(createPetRequest);

    softAssert.assertEquals(
        petCreationResponse.getName(), petName, "pet with given name should be created");
    softAssert.assertEquals(
        petCreationResponse.getStatus(),
        ApplicationConstants.petStatus.SOLD.getPetStatus(),
        "pet status is not correct");

    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void getPetStatus(Method method) {
    logger.info("test execution has started for method " + method.getName());
    GetPetStatusResponse[] response =
        petLib.getPetStatusResponse(ApplicationConstants.petStatus.SOLD.getPetStatus());
    ArrayList<String> petNameList = new ArrayList<>();

    for (int i = 0; i < response.length; i++) {

      if (response[i].getName() != null) {
        petNameList.add(response[i].getName());
      }
    }

    softAssert.assertTrue(
        petNameList.contains(petName), "created pet name given status should be present");

    softAssert.assertAll();
  }
}
