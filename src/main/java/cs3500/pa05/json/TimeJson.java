package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeJson(
    @JsonProperty("hour") int hour,
    @JsonProperty("minute") int minute
) {

}
