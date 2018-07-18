package production.tako.kitchen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderWebJSON {

    @JsonProperty("v")
    private String version;

    @JsonProperty("n")
    private String nonce;

    @JsonProperty("p")
    private String phone;

    @JsonProperty("s")
    private String street;

    @JsonProperty("c")
    private String city;

    @JsonProperty("z")
    private String zip;

    @JsonProperty("d")
    private Float dollarAmt;

    @JsonProperty("o")
    private List<ItemWebJson> orderItems;

}
