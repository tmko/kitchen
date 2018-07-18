package production.tako.kitchen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemWebJson {

    @JsonProperty("i")
    private String id;

    @JsonProperty("t")
    private Integer count;
}
