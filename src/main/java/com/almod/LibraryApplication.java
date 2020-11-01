package com.almod;

import com.almod.enginer.Gender;
import com.almod.entity.Author;
import com.almod.entity.Book;
import com.almod.entity.Genre;
import com.almod.repo.AuthorRepo;
import com.almod.repo.BookRepo;
import com.almod.repo.GenreRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

@SpringBootApplication
public class LibraryApplication {
    private static final Logger log = LoggerFactory.getLogger(LibraryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner loadData(GenreRepo genreRepo, AuthorRepo authorRepo, BookRepo bookRepo) {
        return (args) -> {
            log.info("Genres found with findAll():");
            log.info("-------------------------------");
            for (Genre g : genreRepo.findAll()) {
                log.info(g.toString());
            }

            log.info("Authors found with findAll():");
            log.info("-------------------------------");
            for (Author a : authorRepo.findAll()) {
                log.info(a.toString());
            }

            log.info("Books found with findAll():");
            log.info("-------------------------------");
            for (Book b : bookRepo.findAll()) {
                log.info(b.toString());
            }
        };
    }*/
}
