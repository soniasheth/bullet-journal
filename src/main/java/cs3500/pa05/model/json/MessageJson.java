package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record MessageJson(
    @JsonProperty("key") String messageName,
    @JsonProperty("value") JsonNode arguments) {
}
