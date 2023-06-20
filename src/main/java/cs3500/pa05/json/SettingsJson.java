package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SettingsJson(
    @JsonProperty("name") String name,
    @JsonProperty("email") String email,
    @JsonProperty("eventMax") int eventMax,
    @JsonProperty("taskMax") int taskMax,
    @JsonProperty("categories") List<CategoryJson> categories
){}
