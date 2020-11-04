package com.almod.form;

import com.almod.component.BookComponent;
import com.almod.component.GenreComponent;
import com.almod.entity.Genre;
import com.almod.service.GenreService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GenreForm extends FormLayout {
    private TextField name = new TextField("Name");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button cancel = new Button("Cancel");

    private Binder<Genre> binder = new Binder<>(Genre.class);

    private GenreService genreService;

    private GenreComponent genreComponent;

    public GenreForm(GenreComponent genreComponent, GenreService genreService) {
        this.genreComponent = genreComponent;
        this.genreService = genreService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);
        VerticalLayout items = new VerticalLayout(name, buttons);

        add(items);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        cancel.addClickListener(event -> cancel());
    }

    private void cancel() {
        setVisible(false);
        genreComponent.getCreateGenreButton().setVisible(true);
    }

    private void save() {
        Genre genre = binder.getBean();
        if(!(genre.getName() == null || genre.getName().isEmpty())) {
            genreService.save(genre);
            genreComponent.updateGrid();
            setGenre(null);
        }
    }

    private void delete() {
        Genre genre = binder.getBean();
        if(!(genre.getName() == null || genre.getName().isEmpty())) {
            try {
                genreService.delete(genre);
            } catch (Exception e) {
                Dialog errorDialog = new Dialog();
                errorDialog.add(
                        new Text("You can't delete while there is a book with this genre "),
                        new Button("Close", ev -> errorDialog.close())
                );
                errorDialog.open();
            }
            genreComponent.updateGrid();
            setGenre(null);
        }
    }

    public void setGenre(Genre genre) {
        binder.setBean(genre);

        if (genre == null) {
            setVisible(false);
            genreComponent.getCreateGenreButton().setVisible(true);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
