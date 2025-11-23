# Проект Java / Лабораторная работа №6. Выполнил Денисов Алексей ИТ-13

## Описание проекта
Проект представляет собой реализацию системы пользовательских аннотаций в Java с обработчиками на основе Reflection API. Включает 6 аннотаций, обработчики для них, тесты с JUnit и Mockito, а также дружественный интерфейс для демонстрации работы.

## Структура проекта
```
project/
├── src/main/java/
│   ├── annotations/          # Пользовательские аннотации
│   ├── handlers/            # Обработчики аннотаций
│   ├── model/               # Модели для демонстрации
│   ├── service/             # Сервисные интерфейсы
│   └── app/                 # Главное приложение
├── src/test/java/           # JUnit тесты
└── documentation.xml        # XML документация
```

## Аннотации и их обработчики

### Аннотация @Invoke
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Invoke {
}
```
**Описание:** Помечает методы для автоматического вызова через Reflection API. Обработчик находит и выполняет все методы с этой аннотацией.

**Обработчик:**
```java
public class InvokeProcessor {
    public static void process(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Invoke.class)) {
                m.setAccessible(true);
                m.invoke(instance);
            }
        }
    }
}
```

**Пример использования:**
```java
public class DemoAnnotated {
    @Invoke
    public void sayHello() {
        System.out.println("Hello from DemoAnnotated!");
    }
    
    @Invoke
    public static void staticGreet() {
        System.out.println("Static greeting!");
    }
}
```

### Аннотация @DefaultType
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface DefaultType {
    Class<?> value();
}
```
**Описание:** Указывает тип по умолчанию для класса или поля. Обработчик выводит информацию о заданном типе.

### Аннотация @ToString
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface ToString {
    ToStringMode value() default ToStringMode.YES;
}
```
**Описание:** Управляет включением полей в строковое представление объекта. Поддерживает режимы YES/NO.

**Обработчик:**
```java
public class ToStringProcessor {
    public static String buildString(Object obj) {
        List<String> parts = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            ToString ann = f.getAnnotation(ToString.class);
            ToStringMode mode = ann == null ? ToStringMode.YES : ann.value();
            if (mode == ToStringMode.YES) {
                parts.add(f.getName() + "=" + f.get(obj));
            }
        }
        return clazz.getSimpleName() + "{" + String.join(", ", parts) + "}";
    }
}
```

**Пример использования:**
```java
@ToString
public class Person {
    private String firstName = "John";
    private String lastName = "Doe";
    
    @ToString(ToStringMode.NO)
    private String password = "secret";
}
// Результат: Person{firstName=John, lastName=Doe}
```

### Аннотация @Validate
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Validate {
    Class<?>[] value();
}
```
**Описание:** Содержит список классов для проверки валидации. Обработчик выводит информацию о классах валидации.

### Аннотация @Two
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Two {
    String first();
    int second();
}
```
**Описание:** Аннотация с двумя обязательными свойствами - строкой и числом. Обработчик считывает значения свойств.

### Аннотация @Cache
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cache {
    String[] value() default {};
}
```
**Описание:** Определяет области кеширования для класса. Обработчик возвращает список кешируемых областей.

**Обработчик:**
```java
public class CacheProcessor {
    public static List<String> getCacheAreas(Class<?> clazz) {
        Cache cache = clazz.getAnnotation(Cache.class);
        return cache == null ? List.of() : Arrays.asList(cache.value());
    }
}
```

## Тестирование

### Тесты для @ToString
```java
public class ToStringTest {
    @Test
    public void toString_excludesFieldsAnnotatedNo() {
        Person p = new Person("Anna", "Smith", "pwd123");
        String result = ToStringProcessor.buildString(p);
        assertTrue(result.contains("firstName=Anna"));
        assertTrue(result.contains("lastName=Smith"));
        assertFalse(result.contains("password=pwd123"));
    }
}
```

### Тесты для @Cache с Mockito
```java
public class CacheProcessorTest {
    @Test
    public void cacheAreas_areReadCorrectly() {
        List<String> areas = CacheProcessor.getCacheAreas(CacheUser.class);
        assertEquals(2, areas.size());
        assertTrue(areas.contains("session"));
        assertTrue(areas.contains("profiles"));
    }
    
    @Test
    public void mockCacheService_verifyNoStoreWhenNoAreas() {
        CacheService mockService = Mockito.mock(CacheService.class);
        when(mockService.availableAreas()).thenReturn(List.of());
        verify(mockService, never()).store(anyString(), anyString(), any());
    }
}
```

## Демонстрация работы

### Главное приложение
```java
public class MainApp {
    public static void main(String[] args) {
        // Демонстрация @Invoke
        InvokeProcessor.process(DemoAnnotated.class);
        
        // Демонстрация @ToString
        Person person = new Person("Ivan", "Ivanov", "secret");
        System.out.println(ToStringProcessor.buildString(person));
        
        // Демонстрация @Cache
        List<String> cacheAreas = CacheProcessor.getCacheAreas(CacheUser.class);
        System.out.println("Cache areas: " + cacheAreas);
    }
}
```

**Вывод программы:**
```
Invoked DemoAnnotated.sayHello()
Invoked DemoAnnotated.staticGreet()
Person{firstName=Ivan, lastName=Ivanov}
Cache areas: [session, profiles]
```

## Особенности реализации

- **Reflection API**: Все обработчики используют Reflection для анализа аннотаций
- **Проверка входных данных**: Все публичные методы проверяют параметры на null
- **Обработка исключений**: Корректная обработка IllegalAccessException, InvocationTargetException
- **Гибкая архитектура**: Возможность легко добавлять новые аннотации и обработчики
- **Полное покрытие тестами**: JUnit тесты с Mockito для мок-тестирования

## Заключение
Проект демонстрирует мощь Reflection API в Java для создания систем аннотаций. Реализована полная инфраструктура для объявления аннотаций, их обработки во время выполнения и тестирования. Особое внимание уделено чистоте кода, обработке ошибок и дружественному интерфейсу для демонстрации возможностей системы.
