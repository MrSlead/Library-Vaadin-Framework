package com.almod.service;

import com.almod.entity.Genre;
import com.almod.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService implements GenreRepo {
    @Autowired
    private GenreRepo genreRepo;

    @Override
    public <S extends Genre> S save(S s) {
        return genreRepo.save(s);
    }

    @Override
    public <S extends Genre> Iterable<S> saveAll(Iterable<S> iterable) {
        return genreRepo.saveAll(iterable);
    }

    @Override
    public Optional<Genre> findById(Long aLong) {
        return genreRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return genreRepo.existsById(aLong);
    }

    @Override
    public Iterable<Genre> findAll() {
        return genreRepo.findAll();
    }

    @Override
    public Iterable<Genre> findAllById(Iterable<Long> iterable) {
        return genreRepo.findAllById(iterable);
    }

    @Override
    public long count() {
        return genreRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        genreRepo.deleteById(aLong);
    }

    @Override
    public void delete(Genre genre) {
        genreRepo.delete(genre);
    }

    @Override
    public void deleteAll(Iterable<? extends Genre> iterable) {
        genreRepo.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        genreRepo.deleteAll();
    }
}
