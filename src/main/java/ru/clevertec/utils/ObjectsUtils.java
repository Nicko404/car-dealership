package ru.clevertec.utils;

import java.lang.reflect.Field;

public class ObjectsUtils {

    private ObjectsUtils() {}

    public static void copyNotNullProperties(Object source, Object target) {
        if (source == null || target == null) throw new IllegalArgumentException("Source object and target object are null");
        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();
        if (!sourceClazz.equals(targetClazz)) throw new IllegalArgumentException("Source class and target class are not equal");

        Field[] fields = sourceClazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(target, field.get(source));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
