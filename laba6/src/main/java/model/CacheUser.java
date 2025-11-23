package model;

import annotations.Cache;

/**
 * Пример класса для демонстрации работы аннотации @Cache.
 * Определяет области кеширования для сессии и профилей.
 */
@Cache({"сессия", "профили"})
public class CacheUser {
    private String id;

    /**
     * Конструктор по умолчанию.
     */
    public CacheUser() {}

    /**
     * Конструктор с идентификатором.
     * @param id идентификатор пользователя
     */
    public CacheUser(String id) { this.id = id; }
}