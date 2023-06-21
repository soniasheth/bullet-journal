package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActivityJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("weekday") String weekdayString,
    @JsonProperty("category") CategoryJson categoryJson,
    @JsonProperty("type") String typeString,
    @JsonProperty("completion_status") String completionStatusString,
    @JsonProperty("start_time") TimeJson startTime,
    @JsonProperty("end_time") TimeJson endTime
) {

}
