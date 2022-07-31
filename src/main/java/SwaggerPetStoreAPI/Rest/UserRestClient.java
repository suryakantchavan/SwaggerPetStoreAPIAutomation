package SwaggerPetStoreAPI.Rest;

import SwaggerPetStoreAPI.SpecificationBuilder.SpecBuilder;
import SwaggerPetStoreAPI.dto.Response.CreateUserResponse;
import SwaggerPetStoreAPI.dto.Response.GetUserResponse;

import static io.restassured.RestAssured.given;

public class UserRestClient {


    /**
     *
     * @param obj
     * @return
     */
    public static CreateUserResponse doPost(Object obj,String path){
        return given(SpecBuilder.getSpecRequest()).log().all().body(obj).when()
                .post(path).then().extract().response().as(CreateUserResponse.class);

    }

    /**
     * @return
     */
    public static GetUserResponse doGet(String user,String path) {
        return given(SpecBuilder.getSpecRequest()).log().all().when()
                .get(String.format(path, user)).then().extract().response().as(GetUserResponse.class);

    }


    /**
     * @return
     */
    public static GetUserResponse doPut(Object obj,String user,String path){
        return given(SpecBuilder.getSpecRequest()).log().all().body(obj).when()
                .put(String.format(path, user)).then().extract().response().as(GetUserResponse.class);

    }


}
