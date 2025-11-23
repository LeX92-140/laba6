package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Объявляет кешируемые области для класса.
 * Аннотация применяется только к типам и доступна во время выполнения программы.
 *
 * @author Денисов Алексей ИТ-13
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cache {
    /**
     * Необязательный параметр, содержащий массив имен кешируемых областей
     * @return массив имен областей кеширования
     */
    String[] value() default {};
}
