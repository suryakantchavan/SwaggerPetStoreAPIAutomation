package SwaggerPetStoreAPI.Warpper;

import SwaggerPetStoreAPI.Rest.PetRestClient;
import SwaggerPetStoreAPI.Rest.UserRestClient;
import SwaggerPetStoreAPI.dto.ApplicationConstants;
import SwaggerPetStoreAPI.dto.Request.Category;
import SwaggerPetStoreAPI.dto.Request.CreatePetRequest;
import SwaggerPetStoreAPI.dto.Request.Tag;
import SwaggerPetStoreAPI.dto.Response.GetPetStatusResponse;
import SwaggerPetStoreAPI.dto.Response.PetCreationResponse;
import com.epam.ta.reportportal.ws.annotations.In;

import java.util.ArrayList;
import java.util.List;

public class PetLib {

  /**
   * @param tagName
   * @param tagId
   * @param categoryName
   * @param categoryId
   * @param url
   * @param petName
   * @param petStatus
   * @return
   */
  public CreatePetRequest addNewPetRequest(
      String tagName,
      Integer tagId,
      String categoryName,
      Integer categoryId,
      String url,
      String petName,
      String petStatus,
      Integer petId) {

    Tag tag = Tag.builder().id(tagId).name(tagName).build();
    List<Object> tags = new ArrayList<Object>();
    tags.add(tag);

    List<String> urls = new ArrayList<>();
    urls.add(url);

    Category category = Category.builder().id(categoryId).name(categoryName).build();

    return CreatePetRequest.builder()
        .category(category)
        .tags(tags)
        .name(petName)
        .id(petId)
        .photoUrls(urls)
        .status(petStatus)
        .build();
  }

  /**
   * @return PetCreationResponse
   * @param obj
   */
  public PetCreationResponse getPetCreationResponse(Object obj) {
    PetCreationResponse response =
        PetRestClient.doPost(obj, ApplicationConstants.CREATE_UPDATE_PET_PATH);
    return response;
  }

  /**
   * @param status pet status
   * @return response
   */
  public GetPetStatusResponse[] getPetStatusResponse(String status) {

    GetPetStatusResponse[] response =
        PetRestClient.doGet(status, ApplicationConstants.GET_PET_STATUS_BASE_PATH);
    return response;
  }
}
