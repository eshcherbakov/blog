package org.parma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ComponentScan говорит Spring о том, чтобы просмотреть все компоненты,
 * конфигурации и сервисы в пакете org.parma
 */
@ComponentScan
/**
 * @EnableAutoConfiguration говорит Spring Boot о запуске добавления бинов
 * в соответствии с содержанием classpath и других бинов,
 * а также различных параметров настроек
 */
@EnableAutoConfiguration
public class BlogApp {
    /**
     * Метод main() используется Spring Boot методом SpringApplication.run()
     * для запуска приложения.
     * Подробная информация: http://spring-projects.ru/guides/spring-boot/
     */
   public static void main(String[] args) {
       SpringApplication.run(BlogApp.class, args);
   }
}
