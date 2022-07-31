package SwaggerPetStoreAPI.Warpper;


import SwaggerPetStoreAPI.Rest.UserRestClient;
import SwaggerPetStoreAPI.dto.Request.UserRequest;
import SwaggerPetStoreAPI.dto.Response.CreateUserResponse;
import SwaggerPetStoreAPI.dto.Response.GetUserResponse;
import SwaggerPetStoreAPI.dto.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserLib {

    private static final Logger logger = LogManager.getLogger(UserLib.class);


    /**
     * @param name
     * @return
     */
    public UserRequest createDefaultUserRequest(String name,String userName,int id) {

        return UserRequest.builder().firstName(name).username(userName).id(id).build();

    }

    /**
     * @param name
     * @return
     */
    public UserRequest createDefaultUserUpdateRequest(String name,String userName, int id) {

        return UserRequest.builder().firstName(name).username(userName).id(id).build();

    }

    /**
     *
     * @param obj
     * @return
     */
    public CreateUserResponse getCreateUserResponse(Object obj) {

        CreateUserResponse response = UserRestClient.doPost(obj, ApplicationConstants.CREATE_USER_PATH);
        return response;
    }

    /**
     *
     * @param user
     * @return
     */
    public GetUserResponse getUserInfo(String user){
        GetUserResponse response= UserRestClient.doGet(user, ApplicationConstants.GET_OR_UPDATE_USER_PATH);
        return response;

    }

    /**
     *
     * @param obj
     * @return
     */
    public GetUserResponse updateUserInfo(Object obj,String name) {
        GetUserResponse response= UserRestClient.doPut(obj,name, ApplicationConstants.GET_OR_UPDATE_USER_PATH);
        return response;
    }

}
