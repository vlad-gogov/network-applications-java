package Manager;

import Alarm.BAlarm;
import Alarm.EAlarm;
import Watch.BWatch;
import Watch.EWatch;
import com.google.gson.*;

import java.lang.reflect.Type;

public class EventDeserialize implements JsonDeserializer<Event> {
    @Override
    public Event deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        EventType eventType = EventType.valueOf(obj.get("type").getAsString());
        Event event = new Event(eventType);
        if (obj.has("watch")) {
            JsonObject watch = obj.getAsJsonObject("watch");
            EWatch eWatch = watch.has("seconds_watch") ? EWatch.HMSWatch : EWatch.HMWatch;
            event.watch = BWatch.build(eWatch);
            try {
                event.watch.setHours(watch.get("hours_watch").getAsInt());
                event.watch.setMinutes(watch.get("minutes_watch").getAsInt());
                if (eWatch == EWatch.HMSWatch)
                    event.watch.setSeconds(watch.get("seconds_watch").getAsInt());
            } catch (Exception e) {
                e.printStackTrace();
            }
            event.run = watch.get("run").getAsBoolean();
        }
        if (obj.has("alarm")) {
            JsonObject alarm = obj.getAsJsonObject("alarm");
            EAlarm eAlarm = alarm.has("seconds_alarm") ? EAlarm.HMSAlarm : EAlarm.HMAlarm;
            event.alarm = BAlarm.build(eAlarm);
            try {
                event.alarm.setHours(alarm.get("hours_alarm").getAsInt());
                event.alarm.setMinutes(alarm.get("minutes_alarm").getAsInt());
                if (eAlarm == EAlarm.HMSAlarm)
                    event.alarm.setSeconds(alarm.get("seconds_alarm").getAsInt());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return event;
    }
}
