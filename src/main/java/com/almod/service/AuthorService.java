package com.almod.service;

import com.almod.entity.Author;
import com.almod.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public <S extends Author> S save(S s) {
        return authorRepo.save(s);
    }

    public <S extends Author> Iterable<S> saveAll(Iterable<S> iterable) {
        return authorRepo.saveAll(iterable);
    }

    public Optional<Author> findById(Long aLong) {
        return authorRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return authorRepo.existsById(aLong);
    }

    public Iterable<Author> findAll() {
        return authorRepo.findAll();
    }

    public Iterable<Author> findAllById(Iterable<Long> iterable) {
        return authorRepo.findAllById(iterable);
    }

    public long count() {
        return authorRepo.count();
    }

    public void deleteById(Long aLong) {
        authorRepo.deleteById(aLong);
    }

    public void delete(Author author) {
        authorRepo.delete(author);
    }

    public void deleteAll(Iterable<? extends Author> iterable) {
        authorRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        authorRepo.deleteAll();
    }
}
