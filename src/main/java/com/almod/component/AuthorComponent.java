package com.almod.component;

import com.almod.entity.Author;
import com.almod.service.AuthorService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class AuthorComponent extends HorizontalLayout {
    private final Grid<Author> grid = new Grid<>(Author.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorComponent(AuthorService authorService) {
        this.authorService = authorService;

        setSizeFull();
        configureGrid();
        add(grid);

        updateGrid();
    }

    private void configureGrid() {
        grid.addClassName("author-grid");
        grid.setColumns("id","firstName", "middleName", "lastName");
    }

    private void updateGrid() {
        grid.setItems((Collection) authorService.findAll());
    }
}
