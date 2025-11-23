package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Содержит классы для проверки валидации.
 * Аннотация применяется к типам и другим аннотациям, доступна во время выполнения программы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Validate {
    /**
     * Обязательный параметр, содержащий массив классов для проверки
     * @return массив классов для валидации
     */
    Class<?>[] value();
}
