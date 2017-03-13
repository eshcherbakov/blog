package org.parma.model;

/**
 * Класс приветствия
 */
public class Greeting {
    /**
     * Наименование лица, которого приветствуем
     */
    private final String name;

    /**
     * Текст приветствия
     */
    private static final String greetingTemplate = "Hello, %s!";

    /**
     * Создаёт приветствие
     * @param name Наименование лица, которожго приветствуем
     */
    public Greeting(String name) {
        this.name = name;
    }

    /**
     * Возвращает текст приветствия
     * @return Строка с приветствием
     */
    public String getGreeting() {
        return String.format(greetingTemplate, this.name);
    }
}
