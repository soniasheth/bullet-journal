package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SettingsJson(
    @JsonProperty("name") String name,
    @JsonProperty("email") String email,
    @JsonProperty("eventMax") int eventMax,
    @JsonProperty("taskMax") int taskMax,
    @JsonProperty("start-date") String startDateString
) {

}
