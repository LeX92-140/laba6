package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Связывает класс или поле с типом по умолчанию.
 * Аннотация применяется к типам и полям, доступна во время выполнения программы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface DefaultType {
    /**
     * Обязательный параметр, указывающий класс типа по умолчанию
     * @return класс типа по умолчанию
     */
    Class<?> value();
}
