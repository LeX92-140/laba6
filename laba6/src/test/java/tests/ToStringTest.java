package tests;

import handlers.ToStringProcessor;
import model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <summary>
 * Тесты для ToStringProcessor.
 * Проверяет корректность формирования строкового представления объектов.
 * </summary>
 */
public class ToStringTest {

    /**
     * <summary>
     * Тест проверяет, что поля с аннотацией @ToString(Mode.NO) исключаются из вывода.
     * </summary>
     */
    @Test
    public void toString_excludesFieldsAnnotatedNo() {
        Person p = new Person("Алекс", "Сабер", "пароль");

        String res = ToStringProcessor.buildString(p);

        assertNotNull(res);
        // should contain firstName and lastName
        assertTrue(res.contains("firstName=Алекс") || res.contains("firstName=Алекс")); // safe check
        assertTrue(res.contains("lastName=Сабер"));
        // should not contain password
        assertFalse(res.contains("password=пароль"));
    }

    /**
     * <summary>
     * Тест проверяет, что неаннотированные поля включаются в вывод.
     * </summary>
     */
    @Test
    public void toString_includesUnannotatedFields() {
        Person p = new Person("Серый", "Волк", "x");
        String res = ToStringProcessor.buildString(p);
        assertTrue(res.contains("firstName=Серый"));
        assertTrue(res.contains("lastName=Волк"));
    }
}