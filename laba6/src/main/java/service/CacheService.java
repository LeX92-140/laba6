package service;

import java.util.List;

/**
 * Простой интерфейс, представляющий подсистему кеширования.
 * Легко подменяется моками в тестах.
 */
public interface CacheService {

    /**
     * Сохраняет значение в кеш.
     * @param area область кеширования
     * @param key ключ для сохранения
     * @param value значение для сохранения
     * @return true если сохранение успешно, иначе false
     */
    boolean store(String area, String key, Object value);

    /**
     * Получает значение из кеша.
     * @param area область кеширования
     * @param key ключ для поиска
     * @return найденное значение или null
     */
    Object retrieve(String area, String key);

    /**
     * Получает список доступных областей кеширования.
     * @return список имен областей
     */
    List<String> availableAreas();
}