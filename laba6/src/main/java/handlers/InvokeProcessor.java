package handlers;

import annotations.Invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Находит методы, помеченные аннотацией @Invoke, и вызывает их автоматически.
 * Использует Reflection API для поиска и выполнения методов.
 */
public final class InvokeProcessor {

    private InvokeProcessor() {}

    /**
     * Вызывает аннотированные методы указанного класса.
     * Для статических методов вызывает их напрямую, для нестатических создает экземпляр класса.
     * @param clazz целевой класс для обработки (должен иметь конструктор без параметров для нестатических методов)
     * @throws IllegalArgumentException если clazz равен null
     */
    public static void process(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Аргумент clazz не должен быть null");
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Invoke.class)) {
                boolean isStatic = java.lang.reflect.Modifier.isStatic(m.getModifiers());
                try {
                    m.setAccessible(true);
                    if (isStatic) {
                        m.invoke(null);
                    } else {
                        Constructor<?> ctor = clazz.getDeclaredConstructor();
                        ctor.setAccessible(true);
                        Object instance = ctor.newInstance();
                        m.invoke(instance);
                    }
                    System.out.printf("Вызван метод %s.%s()%n", clazz.getSimpleName(), m.getName());
                } catch (NoSuchMethodException e) {
                    System.err.printf("Не могу вызвать %s.%s(): нет конструктора%n", clazz.getSimpleName(), m.getName());
                } catch (Exception e) {
                    System.err.printf("Ошибка при вызове %s.%s(): %s%n", clazz.getSimpleName(), m.getName(), e.getMessage());
                }
            }
        }
    }
}