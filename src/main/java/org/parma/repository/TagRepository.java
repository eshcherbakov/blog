package org.parma.repository;

import org.parma.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Tag entity.
 */
@SuppressWarnings("unused")
public interface TagRepository extends JpaRepository<Tag,Long> {

}
