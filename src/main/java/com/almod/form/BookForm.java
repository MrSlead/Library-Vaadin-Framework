package com.almod.form;

import com.almod.component.BookComponent;
import com.almod.entity.Author;
import com.almod.entity.Book;
import com.almod.entity.Genre;
import com.almod.entity.Publisher;
import com.almod.service.AuthorService;
import com.almod.service.BookService;
import com.almod.service.GenreService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.Collection;

public class BookForm extends FormLayout {
    private TextField name = new TextField("Name");
    private ComboBox<Author> author = new ComboBox<>("Author");
    private ComboBox<Genre> genre = new ComboBox<>("Genre");
    private ComboBox<Publisher> publisher = new ComboBox<>("Publisher");
    private TextField year = new TextField("Year");
    private TextField city = new TextField("City");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private Binder<Book> binder = new Binder<>(Book.class);

    private BookService bookService;
    private BookComponent bookComponent;



    public BookForm(BookComponent bookComponent,
                    BookService bookService,
                    GenreService genreService,
                    AuthorService authorService) {
        this.bookComponent = bookComponent;
        this.bookService = bookService;

        setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);


        publisher.setItems(Publisher.values());

        genre.setItems((Collection<Genre>) genreService.findAll());
        genre.setItemLabelGenerator(Genre::getName);

        author.setItems((Collection<Author>) authorService.findAll());
        author.setItemLabelGenerator(a -> a.getLastName() + " " + a.getFirstName() + " " + a.getMiddleName());

        HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);
        VerticalLayout items = new VerticalLayout(name, author, genre, publisher, year, city, buttons);

        add(items);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        cancel.addClickListener(event -> cancel());
    }

    private void cancel() {
        setVisible(false);
        bookComponent.getCreateBookButton().setVisible(true);
    }

    private void delete() {
        Book book = binder.getBean();
        //if(checkBookOnEmpty(book)) {
            bookService.delete(book);
            bookComponent.updateGrid();
            setBook(null);
        //}
    }

    private void save() {
        Book book = binder.getBean();
        //if(checkBookOnEmpty(book)) {
            bookService.save(book);
            bookComponent.updateGrid();
            setBook(null);
        //}
    }

    public void setBook(Book book) {
        binder.setBean(book);

        if (book == null) {
            setVisible(false);
            bookComponent.getCreateBookButton().setVisible(true);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}

