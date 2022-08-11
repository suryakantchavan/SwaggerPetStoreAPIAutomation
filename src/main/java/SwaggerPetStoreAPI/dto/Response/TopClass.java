package SwaggerPetStoreAPI.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@ToString
@Generated("jsonschema2pojo")
public class TopClass {

    @JsonProperty("getPetStatusResponses")
    private List<GetPetStatusResponse> getPetStatusResponses=null ;




}
