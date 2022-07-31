package SwaggerPetStoreAPI.dto.Request;


import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tags",
        "status"
})
@Getter
@Setter
@Builder
@Jacksonized
@Generated("jsonschema2pojo")
public class CreatePetRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls = null;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("status")
    private String status;

}