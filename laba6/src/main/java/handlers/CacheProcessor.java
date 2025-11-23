package handlers;

import annotations.Cache;

import java.util.Arrays;
import java.util.List;

/**
 * Читает области кеширования из аннотации @Cache.
 * Предоставляет список кешируемых областей для класса.
 */
public final class CacheProcessor {

    private CacheProcessor() {}

    /**
     * Получает список областей кеширования для указанного класса.
     * @param clazz класс для проверки аннотации @Cache
     * @return список имен областей кеширования или пустой список если аннотация отсутствует или пуста
     * @throws IllegalArgumentException если clazz равен null
     */
    public static List<String> getCacheAreas(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz must not be null");
        }
        Cache cache = clazz.getAnnotation(Cache.class);
        if (cache == null) {
            return List.of();
        }
        String[] areas = cache.value();
        if (areas == null || areas.length == 0) {
            return List.of();
        }
        return Arrays.asList(areas);
    }
}