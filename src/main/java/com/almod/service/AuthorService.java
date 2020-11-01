package com.almod.service;

import com.almod.entity.Author;
import com.almod.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements AuthorRepo {
    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public <S extends Author> S save(S s) {
        return authorRepo.save(s);
    }

    @Override
    public <S extends Author> Iterable<S> saveAll(Iterable<S> iterable) {
        return authorRepo.saveAll(iterable);
    }

    @Override
    public Optional<Author> findById(Long aLong) {
        return authorRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return authorRepo.existsById(aLong);
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepo.findAll();
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> iterable) {
        return authorRepo.findAllById(iterable);
    }

    @Override
    public long count() {
        return authorRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        authorRepo.deleteById(aLong);
    }

    @Override
    public void delete(Author author) {
        authorRepo.delete(author);
    }

    @Override
    public void deleteAll(Iterable<? extends Author> iterable) {
        authorRepo.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        authorRepo.deleteAll();
    }
}
