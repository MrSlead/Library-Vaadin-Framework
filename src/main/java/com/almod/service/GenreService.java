package com.almod.service;

import com.almod.entity.Genre;
import com.almod.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepo genreRepo;

    public <S extends Genre> S save(S s) {
        return genreRepo.save(s);
    }

    public <S extends Genre> Iterable<S> saveAll(Iterable<S> iterable) {
        return genreRepo.saveAll(iterable);
    }

    public Optional<Genre> findById(Long aLong) {
        return genreRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return genreRepo.existsById(aLong);
    }

    public Iterable<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Iterable<Genre> findAllById(Iterable<Long> iterable) {
        return genreRepo.findAllById(iterable);
    }

    public long count() {
        return genreRepo.count();
    }

    public void deleteById(Long aLong) {
        genreRepo.deleteById(aLong);
    }

    public void delete(Genre genre) {
        genreRepo.delete(genre);
    }

    public void deleteAll(Iterable<? extends Genre> iterable) {
        genreRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        genreRepo.deleteAll();
    }

    public Iterable<Genre> findAllByNameToLowerCase(String name) {
        return genreRepo.findAllByNameToLowerCase(name);
    }
}
