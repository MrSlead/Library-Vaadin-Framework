package com.almod.component;

import com.almod.entity.Book;
import com.almod.entity.Genre;
import com.almod.service.BookService;
import com.almod.service.GenreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class BookComponent extends HorizontalLayout {
    private final Grid<Book> grid = new Grid<>(Book.class);

    private final BookService bookService;

    @Autowired
    public BookComponent(BookService bookService) {
        this.bookService = bookService;

        setVisible(false);
        setSizeFull();
        configureGrid();
        add(grid);

        updateGrid();
    }

    private void configureGrid() {
        grid.addClassName("book-grid");
        grid.setColumns("id", "name", "author", "genre", "publisher", "year", "city");
    }

    private void updateGrid() {
        grid.setItems((Collection) bookService.findAll());
    }
}