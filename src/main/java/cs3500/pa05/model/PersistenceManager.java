package cs3500.pa05.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.json.ActivityJson;
import cs3500.pa05.json.CategoryJson;
import cs3500.pa05.json.MessageJson;
import cs3500.pa05.json.SettingsJson;
import cs3500.pa05.json.TimeJson;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.paint.Color;

/**
 * Represents saving and writing to files
 */
public abstract class PersistenceManager {

  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Load data from file to model
   *
   * @param f     File
   * @param model weekdays model
   */
  public static void loadDataFrom(File f, Model model) {
    Set<Category> categories = new HashSet<>(Settings.getInstance().getCategories());
    Settings.getInstance().getCategories().clear();
    List<MessageJson> messages;
    try {
      messages = mapper.readValue(f, new TypeReference<>() {
      });
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return;
    }
    for (MessageJson message : messages) {
      if (message.messageName().equals("setting")) {
        parseSetting(message.arguments());
      } else if (message.messageName().equals("activity")) {
        Activity newActivity = parseActivity(message.arguments());
        categories.add(newActivity.getCategory());
        model.addActivity(newActivity);
      }
    }
    Settings.getInstance().getCategories().addAll(categories);
  }

  /**
   * save data to file
   *
   * @param f     File
   * @param model Weekdays model
   */
  public static void saveDataTo(File f, Model model) {

    List<MessageJson> messageList = new ArrayList<>();
    messageList.add(new MessageJson("setting", getSettingsJsonNode(Settings.getInstance())));
    Map<DayOfWeek, List<Activity>> activities = model.getActivities(null);
    for (DayOfWeek weekday : Settings.getInstance().getDaysOfWeek()) {
      for (Activity activity : activities.get(weekday)) {
        messageList.add(new MessageJson("activity", getActivityJsonNode(activity)));
      }
    }

    try {
      mapper.writeValue(f, messageList);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * parse an activity
   *
   * @param arguments JSON Node
   * @return Activity
   */
  private static Activity parseActivity(JsonNode arguments) {
    Activity activity;
    ActivityJson activityJson = mapper.convertValue(arguments, ActivityJson.class);
    CategoryJson categoryJson = activityJson.categoryJson();
    Category category = new Category(categoryJson.name(), Color.WHITE);
    if (activityJson.typeString().equals(ActivityType.EVENT.name())) {
      TimeJson startTimeJson = activityJson.startTime();
      TimeJson endTimeJson = activityJson.endTime();
      LocalTime startTime = LocalTime.of(startTimeJson.hour(), startTimeJson.minute());
      LocalTime endTime = LocalTime.of(endTimeJson.hour(), endTimeJson.minute());
      activity = new Event(activityJson.name(), activityJson.description(),
        DayOfWeek.valueOf(activityJson.weekdayString()), category, startTime, endTime);
    } else {
      activity = new Task(activityJson.name(), activityJson.description(),
        DayOfWeek.valueOf(activityJson.weekdayString()), category,
        CompletionStatus.valueOf(activityJson.completionStatusString()));
    }
    return activity;
  }

  /**
   * get activity node
   *
   * @param activity Activity
   * @return JsonNode
   */
  private static JsonNode getActivityJsonNode(Activity activity) {
    CategoryJson categoryJson = new CategoryJson(activity.getCategory().getName());
    TimeJson startTime = new TimeJson(-1, -1);
    TimeJson endTime = new TimeJson(-1, -1);
    CompletionStatus cs = CompletionStatus.COMPLETED;
    if (activity.getType() == ActivityType.TASK) {
      Task task = (Task) activity;
      cs = task.getStatus();
    } else {
      Event event = (Event) activity;
      startTime = new TimeJson(event.getStartTime().getHour(), event.getStartTime().getMinute());
      endTime = new TimeJson(event.getEndTime().getHour(), event.getEndTime().getMinute());
    }

    ActivityJson activityJson = new ActivityJson(activity.getName(), activity.getDescription(),
        activity.getWeekday().name(), categoryJson, activity.getType().name(), cs.name(), startTime,
        endTime);

    return serializedRecord(activityJson);
  }

  /**
   * parse settings
   *
   * @param arguments JsonNode
   */
  private static void parseSetting(JsonNode arguments) {
    SettingsJson settingsJson = mapper.convertValue(arguments, SettingsJson.class);
    Settings settings = Settings.getInstance();
    settings.setName(settingsJson.name());
    settings.setEmail(settingsJson.email());
    settings.setTaskMax(settingsJson.taskMax());
    settings.setEventMax(settingsJson.eventMax());
    settings.setLocalDate(
        LocalDate.parse(settingsJson.startDateString(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
  }

  /**
   * get settings JsonNode
   *
   * @param settings Setting class
   * @return JsonNode
   */
  private static JsonNode getSettingsJsonNode(Settings settings) {
    SettingsJson settingsJson = new SettingsJson(settings.getName(), settings.getEmail(),
        settings.getEventMax(), settings.getTaskMax(), settings.getDateString());
    return serializedRecord(settingsJson);
  }

  /**
   * serialize the given record
   *
   * @param record the record to be serialized
   * @return serialized JsonNode
   * @throws IllegalArgumentException throws if unable to serialize the given record
   */
  private static JsonNode serializedRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}