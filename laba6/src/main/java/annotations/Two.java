package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Аннотация, содержащая строковое и целочисленное значения.
 * Аннотация применяется только к типам и доступна во время выполнения программы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Two {
    /**
     * Обязательный строковый параметр
     * @return строковое значение
     */
    String first();

    /**
     * Обязательный целочисленный параметр
     * @return целочисленное значение
     */
    int second();
}
