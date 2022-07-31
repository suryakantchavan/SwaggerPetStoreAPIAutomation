package SwaggerPetStoreAPI.dto;

public class ApplicationConstants {

    public static final String CREATE_USER_PATH="user/createWithArray";
    public static  final String GET_OR_UPDATE_USER_PATH="/user/%s";


    public static final String CREATE_UPDATE_PET_PATH = "/pet";
    public static  final String GET_PET_STATUS_BASE_PATH="/pet/findByStatus";
    public static final String BASE_PATH="/v2";

    public static final String CREATE_USER_RESPONSE_OK="ok";
    public static final String CREATE_USER_RESPONSE_TYPE="unknown";
    public static final Integer CREATE_USER_STATUS_CODE=200;
    public static final Integer SERVER_ERROR=500;
    public static final String CREATE_USER_INVALID_PAYLOAD_RESPONSE_MESSAGE="something bad happened";

    public static final String USER_NOT_AVAILABLE_MESSAGE = "User not found";
    public static final String USER_NOT_FOUND_RESPONSE_TYPE="error";

    public enum petStatus {
        AVAILABLE("available"),
        SOLD("sold"),
        PENDING("pending");

        private final String petStatus;
        petStatus(String petStatus) {
            this.petStatus = petStatus;
        }

        public String getPetStatus() {
            return petStatus;
        }
    }
}
