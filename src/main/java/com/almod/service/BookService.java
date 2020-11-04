package com.almod.service;

import com.almod.entity.Book;
import com.almod.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public <S extends Book> S save(S s) {
        return bookRepo.save(s);
    }

    public <S extends Book> Iterable<S> saveAll(Iterable<S> iterable) {
        return bookRepo.saveAll(iterable);
    }

    public Optional<Book> findById(Long aLong) {
        return bookRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return bookRepo.existsById(aLong);
    }

    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }

    public Iterable<Book> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return bookRepo.findAll();
        } else {
            return search(stringFilter);
        }
    }

    public Iterable<Book> findAllById(Iterable<Long> iterable) {
        return bookRepo.findAllById(iterable);
    }

    public long count() {
        return bookRepo.count();
    }

    public void deleteById(Long aLong) {
        bookRepo.deleteById(aLong);
    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }

    public void deleteAll(Iterable<? extends Book> iterable) {
        bookRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        bookRepo.deleteAll();
    }

    public List<Book> search(String searchTerm) {
        return bookRepo.search(searchTerm);
    }
}
