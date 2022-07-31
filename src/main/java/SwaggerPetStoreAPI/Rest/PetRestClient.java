package SwaggerPetStoreAPI.Rest;

import SwaggerPetStoreAPI.SpecificationBuilder.SpecBuilder;
import SwaggerPetStoreAPI.dto.Response.GetPetStatusResponse;
import SwaggerPetStoreAPI.dto.Response.GetUserResponse;
import SwaggerPetStoreAPI.dto.Response.PetCreationResponse;

import static io.restassured.RestAssured.given;

public class PetRestClient {


    /**
     *
     * @param obj
     * @return
     */
    public static PetCreationResponse doPost(Object obj, String path){
        return given(SpecBuilder.getSpecRequest()).log().all().body(obj).when()
                .post(path).then().extract().response().as(PetCreationResponse.class);

    }

    /**
     * @return
     */
    public static GetPetStatusResponse[] doGet(String status, String path) {
        return given(SpecBuilder.getSpecRequest()).log().all().when().queryParam("status",status)
                .get(path).then().extract().response().as(GetPetStatusResponse[].class);

    }


    /**
     * @return
     */
    public static GetUserResponse doPut(Object obj,String user,String path){
        return given(SpecBuilder.getSpecRequest()).log().all().body(obj).when()
                .put(String.format(path, user)).then().extract().response().as(GetUserResponse.class);

    }
}
