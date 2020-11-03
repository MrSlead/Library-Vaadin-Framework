package com.almod.component;

import com.almod.entity.Author;
import com.almod.form.AuthorForm;
import com.almod.service.AuthorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class AuthorComponent extends VerticalLayout {
    private final Grid<Author> grid = new Grid<>(Author.class);
    private final Button createAuthor = new Button("Add author");
    private final AuthorService authorService;

    @Autowired
    public AuthorComponent(AuthorService authorService) {
        this.authorService = authorService;

        AuthorForm authorForm = new AuthorForm(this, authorService);
        authorForm.setVisible(false);

        createAuthor.addClickListener(e -> {
            authorForm.setAuthor(new Author());
            createAuthor.setVisible(false);
        });

        grid.asSingleSelect().addValueChangeListener(e -> {
            createAuthor.setVisible(false);
            authorForm.setAuthor(e.getValue());
        });

        setSizeFull();
        configureGrid();
        setAlignItems(Alignment.CENTER);
        add(grid, createAuthor, authorForm);

        updateGrid();
    }

    private void configureGrid() {
        grid.addClassName("author-grid");
        grid.setColumns("id","firstName", "middleName", "lastName");
    }

    public void updateGrid() {
        grid.setItems((Collection) authorService.findAll());
    }

    public Button getCreateAuthorButton() {
        return createAuthor;
    }
}
