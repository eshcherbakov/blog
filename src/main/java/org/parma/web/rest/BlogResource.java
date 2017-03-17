package org.parma.web.rest;

import org.parma.domain.Blog;
import org.parma.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @RestController объединяет в себе аннотации @Controller и @ResponseBody.
 *
 * @Controller - указывает, что класс является контроллером.
 * @ResponseBody используется для того, чтобы результат работы метода в контроллере
 * был выведен непосредственно в тело ответа за запрос, а не послужил адресом перехода и не был
 * перемещён как параметр в модель.
 * Дополнительная информация:
 * http://ru.java.wikia.com/wiki/@RestController,
 * http://ru.java.wikia.com/wiki/Контроллер,
 * http://ru.java.wikia.com/wiki/@ResponseBody
 */
@RestController
/**
 * @RequestMapping - предназначена для того, чтобы задать методам контроллера адреса,
 * по которым они будут доступны на клиенте.
 * Применяется как для класса-контроллера, так и для отдельного метода в нём.
 * Дополнительная информация: http://ru.java.wikia.com/wiki/@RequestMapping
 */
@RequestMapping("/api")
public class BlogResource {

    /**
     * Подключаем систему логирования
     * Дополнительная информация: https://habrahabr.ru/post/247647/
     */
    private final Logger log = LoggerFactory.getLogger(BlogResource.class);

    /**
     * Наименование сущности
     */
    private static final String BLOG_ENTITY_NAME = "blog";

    /**
     * Репозиторий блога
     */
    private final BlogRepository blogRepository;

    /**
     * Делаем инъекцию зависимости от репозитория блога в конструкторе
     * @param blogRepository
     */
    public BlogResource(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * GET /blogs : возвращает список всех блогов
     * @return Возвращает ResponseEntity со HTTP-статусом 500 и со списком блогов в теле ответа
     *
     * ResponseEntity является обёрткой для ответа и дополнительно для HTTP заголовков и кода статуса.
     */
    @GetMapping("/blogs")
    public List<Blog> getAllBlogs() {
        log.debug("REST-запрос для получения всех блогов");
        return blogRepository.findAll();
    }
}
