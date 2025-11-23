package app;

import handlers.AnnotationProcessors;
import handlers.InvokeProcessor;
import handlers.ToStringProcessor;
import handlers.CacheProcessor;
import model.DemoAnnotated;
import model.Person;
import model.CacheUser;

import java.util.List;
import java.util.Scanner;

/**
 * Дружественное консольное приложение, демонстрирующее работу аннотаций и обработчиков.
 * Предоставляет интерактивный интерфейс для тестирования функциональности.
 */
public final class MainApp {

    private MainApp() {}

    /**
     * Главный метод приложения, демонстрирующий работу всех аннотаций и обработчиков.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println("Аннотации");

        DemoAnnotated demo = new DemoAnnotated();

        // 1) Invoke annotated methods
        System.out.println("\nВызов методов помеченных @Invoke в DemoAnnotated");
        InvokeProcessor.process(DemoAnnotated.class);

        // 2) DefaultType
        System.out.println("\n@DefaultType");
        AnnotationProcessors.processDefaultType(DemoAnnotated.class);

        // 3) ToString usage
        System.out.println("\n@ToString");
        String s = ToStringProcessor.buildString(demo);
        System.out.println("ToStringProcessor - " + s);

        // 4) Validate
        System.out.println("\n@Validate");
        AnnotationProcessors.processValidate(DemoAnnotated.class);

        // 5) Two
        System.out.println("\n@Two");
        AnnotationProcessors.processTwo(DemoAnnotated.class);

        // 6) Cache
        System.out.println("\n@Cache");
        AnnotationProcessors.processCache(DemoAnnotated.class);

        // Demo person
        System.out.println("\nToString для person");
        Person p = new Person("Алекс", "Сабер", "пароль");
        System.out.println(ToStringProcessor.buildString(p));

        // Show cache areas via CacheProcessor
        System.out.println("\n-- CacheProcessor для CacheUser --");
        List<String> areas = CacheProcessor.getCacheAreas(CacheUser.class);
        if (areas.isEmpty()) {
            System.out.println("Нет кеш областей");
        } else {
            System.out.println("Кеш области: " + areas);
        }

        // Friendly prompt demonstrating input validation
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nВведите имя класса для проверки (DemoAnnotated/Person/CacheUser) или 'exit':");
            String line = scanner.nextLine().trim();
            if (!"exit".equalsIgnoreCase(line)) {
                switch (line) {
                    case "DemoAnnotated":
                        InvokeProcessor.process(DemoAnnotated.class);
                        System.out.println(ToStringProcessor.buildString(demo));
                        break;
                    case "Person":
                        System.out.println(ToStringProcessor.buildString(p));
                        break;
                    case "CacheUser":
                        System.out.println("Области кеширования CacheUser: " + CacheProcessor.getCacheAreas(CacheUser.class));
                        break;
                    default:
                        System.out.println("Нет такого( Завершение...");
                }
            }
        }
    }
}