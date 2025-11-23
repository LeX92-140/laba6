package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Помечает методы, которые должны быть автоматически вызваны с помощью рефлексии.
 * Аннотация применяется только к методам и доступна во время выполнения программы.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Invoke {
}
