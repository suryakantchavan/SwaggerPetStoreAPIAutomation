package SwaggerPetStoreAPI.dto.Request;


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
        "name"
})
@Getter
@Setter
@Builder
@Jacksonized
@Generated("jsonschema2pojo")
public class Tag {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
}