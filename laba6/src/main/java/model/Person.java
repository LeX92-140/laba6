package model;

import annotations.ToString;
import annotations.ToStringMode;

/**
 * Пример модели для тестирования функциональности ToString.
 * Демонстрирует включение и исключение полей из строкового представления.
 */
@ToString
public class Person {

    private String firstName;
    private String lastName;

    /**
     * Пароль, исключенный из строкового представления.
     */
    @ToString(ToStringMode.NO)
    private String password;

    /**
     * Конструктор по умолчанию.
     */
    public Person() {}

    /**
     * Конструктор с параметрами.
     * @param firstName имя человека
     * @param lastName фамилия человека
     * @param password пароль (не отображается в toString)
     */
    public Person(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /**
     * Получает имя.
     * @return имя
     */
    public String getFirstName() { return firstName; }

    /**
     * Получает фамилию.
     * @return фамилия
     */
    public String getLastName() { return lastName; }

    /**
     * Получает пароль.
     * @return пароль
     */
    public String getPassword() { return password; }
}