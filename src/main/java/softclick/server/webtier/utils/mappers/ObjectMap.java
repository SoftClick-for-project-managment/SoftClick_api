package softclick.server.webtier.utils.mappers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectMap {
    public static Map<Object, Object> toMap(Object object) {
        Map<Object, Object> map = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(object) != null)
                    map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
