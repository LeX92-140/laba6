package handlers;

import annotations.ToString;
import annotations.ToStringMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Строит строковое представление объектов на основе аннотаций @ToString.
 * Учитывает аннотации как на уровне класса, так и на уровне полей.
 */
public final class ToStringProcessor {

    private ToStringProcessor() {}

    /**
     * Создает строковое представление объекта, учитывая аннотации @ToString.
     * Правила:
     * - Если класс помечен @ToString - включается обработка
     * - Поле включается если не аннотировано или аннотировано с YES
     * - Поле исключается если аннотировано с NO
     * @param obj объект для преобразования в строку
     * @return строковое представление объекта
     */
    public static String buildString(Object obj) {
        if (obj == null) {
            return "null";
        }
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(ToString.class)) {
            // fallback to default toString
            return obj.toString();
        }

        List<String> parts = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            ToString ann = f.getAnnotation(ToString.class);
            ToStringMode mode = ann == null ? ToStringMode.YES : ann.value();
            if (mode == ToStringMode.YES) {
                try {
                    Object value = f.get(obj);
                    parts.add(f.getName() + "=" + String.valueOf(value));
                } catch (IllegalAccessException e) {
                    // ignore field on error
                }
            }
        }
        return String.format("%s{%s}", clazz.getSimpleName(), String.join(", ", parts));
    }
}
