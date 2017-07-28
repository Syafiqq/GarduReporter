package app.freelancer.syafiqq.gardureporter.model.gson.serializer.custom;

import app.freelancer.syafiqq.gardureporter.model.dao.Location;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Locale;


/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 13 July 2017, 11:51 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Location14DigitSerializer implements JsonSerializer<Location>
{
    @Override public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject obj = new JsonObject();
        obj.addProperty("latitude", String.format(Locale.US, "%.14g", location.getLatitude()));
        obj.addProperty("longitude", String.format(Locale.US, "%.14g", location.getLongitude()));
        return obj;
    }
}
