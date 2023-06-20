package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryJson(
    @JsonProperty("name") String name,
    @JsonProperty("colorHex") String colorHex
) {

}
