package org.parma.repository;

import org.parma.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA-репозиторий для сущности блога.
 */
@SuppressWarnings("unused")
public interface BlogRepository extends JpaRepository<Blog,Long> {
}
