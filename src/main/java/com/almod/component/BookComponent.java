package com.almod.component;

import com.almod.entity.Book;
import com.almod.entity.Genre;
import com.almod.form.AuthorForm;
import com.almod.service.BookService;
import com.almod.service.GenreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@UIScope
public class BookComponent extends VerticalLayout {
    private final Grid<Book> grid = new Grid<>();
    private TextField filterText = new TextField();

    private final BookService bookService;

    @Autowired
    public BookComponent(BookService bookService) {
        this.bookService = bookService;

        setVisible(false);
        setSizeFull();
        configureGrid();
        configureTextField();
        add(filterText, grid);

        updateGrid();
    }

    private void configureTextField() {
        filterText.setPlaceholder("Filter by book, author, publisher");
        filterText.setWidth("30%");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateGrid());
    }

    private void configureGrid() {
        grid.addClassName("book-grid");

        //grid.addColumn(Book::getId).setHeader("Id");
        grid.addColumn(Book::getName).setHeader("Name");
        grid.addColumn(b ->
                String.format("%s %s %s",
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getMiddleName(),
                        b.getAuthor().getLastName()))
                .setHeader("Author");
        grid.addColumn(b -> b.getPublisher().getName()).setHeader("Genre");
        grid.addColumn(Book::getYear).setHeader("Year");
        grid.addColumn(Book::getCity).setHeader("City");
        //grid.setColumns("id", "name", "author", "genre", "publisher", "year", "city");
    }

    public void updateGrid() {
        grid.setItems((Collection) bookService.findAll(filterText.getValue()));
    }
}