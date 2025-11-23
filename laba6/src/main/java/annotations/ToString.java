package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Помечает тип или поле для включения/исключения в генерируемой строке.
 * Аннотация применяется к типам и полям, доступна во время выполнения программы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ToString {
    /**
     * Необязательный параметр, определяющий режим отображения поля
     * @return режим отображения (по умолчанию YES)
     */
    ToStringMode value() default ToStringMode.YES;
}
