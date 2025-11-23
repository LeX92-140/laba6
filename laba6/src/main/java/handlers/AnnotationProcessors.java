package handlers;

import annotations.Cache;
import annotations.DefaultType;
import annotations.Validate;
import annotations.Two;

import java.util.Arrays;

/**
 * Вспомогательные обработчики для различных аннотаций.
 * Предоставляет методы для чтения и вывода информации из аннотаций.
 */
public final class AnnotationProcessors {

    private AnnotationProcessors() {}

    /**
     * Обрабатывает аннотацию @DefaultType и выводит информацию о типе по умолчанию.
     * @param clazz класс для проверки аннотации
     */
    public static void processDefaultType(Class<?> clazz) {
        DefaultType dt = clazz.getAnnotation(DefaultType.class);
        if (dt != null) {
            System.out.printf("@DefaultType on %s -> %s%n", clazz.getSimpleName(), dt.value().getName());
        } else {
            System.out.printf("Аннотация @DefaultType отсутствует у %s%n", clazz.getSimpleName());
        }
    }

    /**
     * Обрабатывает аннотацию @Validate и выводит информацию о классах для проверки.
     * @param clazz класс для проверки аннотации
     */
    public static void processValidate(Class<?> clazz) {
        Validate v = clazz.getAnnotation(Validate.class);
        if (v != null) {
            System.out.printf("@Validate on %s -> %s%n", clazz.getSimpleName(), Arrays.toString(v.value()));
        } else {
            System.out.printf("Аннотация @Validate отсутствует у %s%n", clazz.getSimpleName());
        }
    }

    /**
     * Обрабатывает аннотацию @Two и выводит значения ее свойств.
     * @param clazz класс для проверки аннотации
     */
    public static void processTwo(Class<?> clazz) {
        Two t = clazz.getAnnotation(Two.class);
        if (t != null) {
            System.out.printf("@Two on %s -> first='%s', second=%d%n", clazz.getSimpleName(), t.first(), t.second());
        } else {
            System.out.printf("Аннотация @Two отсутствует у %s%n", clazz.getSimpleName());
        }
    }

    /**
     * Обрабатывает аннотацию @Two и выводит значения ее свойств.
     * @param clazz класс для проверки аннотации
     */
    public static void processCache(Class<?> clazz) {
        Cache c = clazz.getAnnotation(Cache.class);
        if (c == null || c.value().length == 0) {
            System.out.printf("Кеш области отсутствует для %s%n", clazz.getSimpleName());
        } else {
            System.out.printf("Кеш области для %s: %s%n", clazz.getSimpleName(), Arrays.toString(c.value()));
        }
    }
}