package SwaggerPetStoreAPI.SpecificationBuilder;

import SwaggerPetStoreAPI.dto.ApplicationConstants;
import Util.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;



public class SpecBuilder {

    /**
     * this is request specification builder to build request body
     * @return request body
     */
    public static RequestSpecification getSpecRequest() {

        return new RequestSpecBuilder().setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .setBasePath(ApplicationConstants.BASE_PATH)
                .setContentType(ContentType.JSON).log(LogDetail.ALL).build();
    }

}
