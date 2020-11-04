package com.almod.form;

import com.almod.component.AuthorComponent;
import com.almod.entity.Author;
import com.almod.service.AuthorService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AuthorForm extends FormLayout {
    private TextField lastName = new TextField("Last name");
    private TextField firstName = new TextField("First name");
    private TextField middleName = new TextField("Middle name");

    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private Binder<Author> binder = new Binder<>(Author.class);

    private AuthorService authorService;

    private AuthorComponent authorComponent;

    public AuthorForm(AuthorComponent authorComponent, AuthorService authorService) {
        this.authorComponent = authorComponent;
        this.authorService = authorService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);
        VerticalLayout items = new VerticalLayout(lastName, firstName, middleName, buttons);

        add(items);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        cancel.addClickListener(event -> cancel());
    }

    private void cancel() {
        setVisible(false);
        authorComponent.getCreateAuthorButton().setVisible(true);
    }

    private void delete() {
        Author author = binder.getBean();
        if(checkAuthorOnEmpty(author)) {
            try {
                authorService.delete(author);
            } catch (Exception e) {
                Dialog errorDialog = new Dialog();
                errorDialog.add(
                        new Text("You can't delete while there is a book with this author "),
                        new Button("Close", ev -> errorDialog.close())
                );
                errorDialog.open();
            }
            authorComponent.updateGrid();
            setAuthor(null);
        }
    }

    private void save() {
        Author author = binder.getBean();
        if(checkAuthorOnEmpty(author)) {
            authorService.save(author);
            authorComponent.updateGrid();
            setAuthor(null);
        }
    }

    private boolean checkAuthorOnEmpty(Author author) {
        if(!((author.getFirstName() == null || author.getFirstName().isEmpty()) &&
                (author.getLastName() == null || author.getLastName().isEmpty()) &&
                (author.getMiddleName() == null || author.getMiddleName().isEmpty())))
        {
            return true;
        }

        return false;
    }

    public void setAuthor(Author author) {
        binder.setBean(author);

        if (author == null) {
            setVisible(false);
            authorComponent.getCreateAuthorButton().setVisible(true);
        } else {
            setVisible(true);
            lastName.focus();
        }
    }
}


