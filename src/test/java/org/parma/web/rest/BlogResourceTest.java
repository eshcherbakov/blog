package org.parma.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.parma.BlogApp;
import org.parma.domain.Blog;
import org.parma.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Необходимо предоставить доступ к контексту Spring для тестирования контроллера
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApp.class)
public class BlogResourceTest {
    // Внедряем в тест менеджер сущностей
    @Autowired
    private EntityManager em;

    // Внедряем репозиторий для работы с блогом
    @Autowired
    private BlogRepository blogRepository;

    // Объявляем обёртку, имитирующую работу REST-контролллера
    private MockMvc restBlogMockMvc;

    // Объявляем экземпляр блога, с которым будем работать в тесте
    private Blog blog;

    /**
     * Метод, отмеченный аннотацией @Before, выполняется перед каждым тестом.
     * Создаёт экземпляр сущности блога для каждого теста.
     *
     * @throws Exception Исключение
     */
    @Before
    public void initTest() throws Exception {
        this.blog = BlogResourceTest.createBlogEntity(this.em);
    }

    /**
     * Перед выполнением каждого теста настраиваем обёртку, имитурующую работу REST-контроллера
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BlogResource blogResource = new BlogResource(this.blogRepository);
        this.restBlogMockMvc = MockMvcBuilders.standaloneSetup(blogResource).build();
    }

    /**
     * Создаёт сущность блога для тестирования
     * @param em Менеджер сущностей
     * @return Блог
     */
    public static Blog createBlogEntity(EntityManager em) {
        Blog blog = new Blog();
        blog.setName("Блог 1");
        blog.setDescription("Описание блога 1");

        return blog;
    }

    @Test
    public void getAllBlogs() throws Exception {
        // Сохраняем новый блог в базе данных
        blogRepository.saveAndFlush(this.blog);

        // Получаем список блогов по GET-запросу
        restBlogMockMvc.perform(get("/api/blogs"))
                // Ожидаем ответ со HTTP-статусом 200 OK
                .andExpect(status().isOk())
                // Ожидаем ответ с типом содержимого "application/json;charset=UTF-8"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // Ожидаем ответ, содержающий массив с JSON-объектами,
                // среди которых есть тот, который был сохранён в базе
                .andExpect(jsonPath("$.[*].id").value(hasItem(this.blog.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(this.blog.getName())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(this.blog.getDescription())));
    }

}