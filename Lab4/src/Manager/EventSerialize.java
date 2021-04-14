package Manager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EventSerialize implements JsonSerializer<Event> {
    @Override
    public JsonElement serialize(Event event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject res = new JsonObject();
        res.addProperty("type", String.valueOf(event.type));
        if (event.watch != null) {
            JsonObject watch = new JsonObject();
            watch.addProperty("hours_watch", event.watch.getHours());
            watch.addProperty("minutes_watch", event.watch.getMinutes());
            try {
                watch.addProperty("seconds_watch", event.watch.getSeconds());
            } catch (Exception e) {
                e.printStackTrace();
            }
            watch.addProperty("run", event.run);
            res.add("watch", watch);
        }
        if (event.alarm != null) {
            JsonObject alarm = new JsonObject();
            alarm.addProperty("hours_alarm", event.alarm.getHours());
            alarm.addProperty("minutes_alarm", event.alarm.getMinutes());
            try {
                alarm.addProperty("seconds_alarm", event.alarm.getSeconds());
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.add("alarm", alarm);
        }
        return res;
    }
}
