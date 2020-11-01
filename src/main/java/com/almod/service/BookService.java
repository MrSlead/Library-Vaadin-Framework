package com.almod.service;

import com.almod.entity.Book;
import com.almod.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService implements BookRepo {
    @Autowired
    private BookRepo bookRepo;

    @Override
    public <S extends Book> S save(S s) {
        return bookRepo.save(s);
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> iterable) {
        return bookRepo.saveAll(iterable);
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return bookRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return bookRepo.existsById(aLong);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> iterable) {
        return bookRepo.findAllById(iterable);
    }

    @Override
    public long count() {
        return bookRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepo.deleteById(aLong);
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {
        bookRepo.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        bookRepo.deleteAll();
    }
}
