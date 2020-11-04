package com.almod.component;

import com.almod.entity.Genre;
import com.almod.form.GenreForm;
import com.almod.service.GenreService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class GenreComponent extends VerticalLayout {
    private final Grid<Genre> grid = new Grid<>(Genre.class);
    private final Button createGenre = new Button("Add genre");

    private final GenreService genreService;

    @Autowired
    public GenreComponent(GenreService genreService) {
        this.genreService = genreService;

        GenreForm genreForm = new GenreForm(this, genreService);
        genreForm.setVisible(false);

        createGenre.addClickListener(e -> {
            genreForm.setGenre(new Genre());
            createGenre.setVisible(false);
        });

        grid.asSingleSelect().addValueChangeListener(e -> {
            createGenre.setVisible(false);
            genreForm.setGenre(e.getValue());
        });

        setVisible(false);
        setSizeFull();
        configureGrid();
        setAlignItems(Alignment.CENTER);
        add(grid, createGenre, genreForm);

        updateGrid();
    }

    private void configureGrid() {
        grid.addClassName("genre-grid");
        grid.setColumns("id", "name");
    }

    public void updateGrid() {
        grid.setItems((Collection) genreService.findAll());
    }

    public Button getCreateGenreButton() {
        return createGenre;
    }

    public Grid<Genre> getGrid() {
        return grid;
    }
}