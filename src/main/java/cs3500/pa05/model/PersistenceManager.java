package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Utils;
import cs3500.pa05.json.CategoryJson;
import cs3500.pa05.json.SettingsJson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * Represents saving and writing to files
 */
public abstract class PersistenceManager {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static void loadSettingsFrom(String path) {
    SettingsJson m;
    try{
       m = mapper.readValue(new File(path), SettingsJson.class);
    }catch (IOException e){
      System.err.println(e.getMessage());
      return;
    }
    Settings.getInstance().setName(m.name());
    Settings.getInstance().setEmail(m.email());
    Settings.getInstance().setTaskMax(m.taskMax());
    Settings.getInstance().setEventMax(m.eventMax());
    List<CategoryJson> categoryJsonList = m.categories();
    for (CategoryJson categoryJson : categoryJsonList) {
      Settings.getInstance().getCategories().add(
          new Category(categoryJson.name(), Color.web(categoryJson.colorHex())));
    }
  }

  public static void saveSettingsTo(String path) {
    List<CategoryJson> categoryJsonList = new ArrayList<>();
    for (Category category : Settings.getInstance().getCategories()) {
      categoryJsonList.add(new CategoryJson(category.getName(), Utils.toHex(category.getColor())));
    }
    Settings instance = Settings.getInstance();
    SettingsJson settingsJson = new SettingsJson(instance.getName(), instance.getEmail(),
        instance.getEventMax(), instance.getTaskMax(), categoryJsonList);
    try {
      mapper.writeValue(new File(path), serializedRecord(settingsJson));
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void writeTo(Appendable appendable, Object object) {
    try {
      appendable.append(mapper.writeValueAsString(object));
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
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