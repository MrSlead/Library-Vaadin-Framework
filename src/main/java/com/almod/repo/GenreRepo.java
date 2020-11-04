package com.almod.repo;

import com.almod.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends CrudRepository<Genre, Long> {
    @Query("select g from Genre g " +
            "where lower(g.name) like lower(concat('%', :searchTerm, '%'))")
    Iterable<Genre> findAllByNameToLowerCase(@Param("searchTerm") String name);
}
