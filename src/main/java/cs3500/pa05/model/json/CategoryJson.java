package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryJson(
    @JsonProperty("name") String name
) {

}
