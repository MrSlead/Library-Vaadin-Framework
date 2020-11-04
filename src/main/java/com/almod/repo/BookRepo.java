package com.almod.repo;

import com.almod.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
    @Query("select b from Book b " +
            "where lower(b.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(b.author.firstName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(b.author.middleName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(b.author.lastName) like lower(concat('%', :searchTerm, '%'))") //
    List<Book> search(@Param("searchTerm") String searchTerm); //
}
