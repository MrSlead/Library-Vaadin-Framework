package com.almod.component;

import com.almod.entity.Genre;
import com.almod.service.GenreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class GenreComponent extends HorizontalLayout {
    private final Grid<Genre> grid = new Grid<>(Genre.class);

    private final GenreService genreService;

    @Autowired
    public GenreComponent(GenreService genreService) {
        this.genreService = genreService;

        setVisible(false);
        setSizeFull();
        configureGrid();
        add(grid);

        updateGrid();
    }

    private void configureGrid() {
        grid.addClassName("genre-grid");
        grid.setColumns("id", "name");
    }

    private void updateGrid() {
        grid.setItems((Collection) genreService.findAll());
    }
}