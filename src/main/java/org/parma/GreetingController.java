package org.parma;

import org.parma.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс, помеченный как @RestController, означает,
 * что он готов к использованию Spring MVC для обработки HTTP-запросов
 */
@RestController
public class GreetingController {

    /**
     * При поступлении GET-запроса / будет выполнен метод greeting.
     * Пример запроса: http://localhost:8080/?name=Evgeniy
     * @param name Наименование лица, которого приветствуем.
     * @return Возвращает JSON-объект с приветствием
     */
    @GetMapping("/")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(name);
    }
}
