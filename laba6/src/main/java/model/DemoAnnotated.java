package model;

import annotations.Invoke;
import annotations.DefaultType;
import annotations.ToString;
import annotations.ToStringMode;
import annotations.Validate;
import annotations.Two;
import annotations.Cache;

/**
 * Демонстрационный класс, аннотированный пользовательскими аннотациями.
 * Используется для демонстрации работы всех обработчиков аннотаций.
 */
@ToString //аннотация на уровне класса включает обработку toString
@DefaultType(String.class)
@Validate({Integer.class, Double.class})
@Two(first = "пример", second = 42)
@Cache({"пользователи", "продукты"})
public class DemoAnnotated {

    /**
     * Секретное поле, исключенное из строкового представления.
     */
    @ToString(ToStringMode.NO)
    private String secret = "не должен быть показан";

    private String name = "Демка";
    private int count = 7;

    /**
     * Конструктор по умолчанию.
     */
    public DemoAnnotated() {
    }

    /**
     * Метод, помеченный для автоматического вызова.
     */
    @Invoke
    public void sayHello() {
        System.out.println("Экземпляр DemoAnnotated!");
    }

    /**
     * Статический метод, помеченный для автоматического вызова.
     */
    @Invoke
    public static void staticGreet() {
        System.out.println("Статическое приветствие DemoAnnotated!");
    }

    /**
     * Получает имя.
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получает счетчик.
     * @return счетчик
     */
    public int getCount() {
        return count;
    }
}