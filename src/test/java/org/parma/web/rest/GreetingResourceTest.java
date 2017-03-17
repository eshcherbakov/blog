package org.parma.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parma.BlogApp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.MockitoAnnotations;

/**
 * Необходимо предоставить доступ к контексту Spring для тестирования контроллера.
 * @RunWith устанавливает класс запуска теста.
 * @SpringBootText устанавливает настройки по умолчанию для выполнения теста в Spring Boot.
 * Параметр classes указывает класс конфигурации с описанием контекста Spring-приложения.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApp.class)
public class GreetingResourceTest {

    /**
     * Обёртка, имитирующая работу REST-контроллера для тестирования его методов
     */
    private MockMvc restGreetingMockMvc;

    /**
     * Метод, помеченный аннотацией @Before, будет выполняться перед каждым тестом
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GreetingResource greetingResource = new GreetingResource();
        this.restGreetingMockMvc = MockMvcBuilders.standaloneSetup(greetingResource)
                .build();
    }

    @Test
    public void greeting() throws Exception {
        // Имитируем выполнение запроса GET /
        this.restGreetingMockMvc.perform(get("/"))
                // Ожидаем ответ со статусом 200 OK
                .andExpect(status().isOk())
                // Ожидаем ответ с типом содержимого "application/json;charset=UTF-8"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // Ожидаем ответ, содержающий JSON
                // {"greeting":"Hello, World!"}
                .andExpect(jsonPath("$.greeting").value("Hello, World!"));
    }

    @Test
    public void greetingWithName() throws Exception {
        // Имитируем выполнение запроса GET /?name=Evgeniy
        this.restGreetingMockMvc.perform(get("/?name=Evgeniy"))
                // Ожидаем ответ со статусом 200 OK
                .andExpect(status().isOk())
                // Ожидаем ответ с типом содержимого "application/json;charset=UTF-8"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // Ожидаем ответ, содержающий JSON
                // {"greeting":"Hello, Evgeniy!"}
                .andExpect(jsonPath("$.greeting").value("Hello, Evgeniy!"));
    }
}