package com.almod.view;

import com.almod.component.AuthorComponent;
import com.almod.component.BookComponent;
import com.almod.component.GenreComponent;
import com.almod.component.TabsEntity;
import com.almod.form.AuthorForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route("library")
public class MainView extends VerticalLayout {

    private final TabsEntity tabsEntity;
    private final AuthorComponent authorComponent;
    private final GenreComponent genreComponent;
    private final BookComponent bookComponent;


    @Autowired
    public MainView(TabsEntity tabsEntity,
                    AuthorComponent authorComponent,
                    GenreComponent genreComponent,
                    BookComponent bookComponent)
    {
        this.tabsEntity = tabsEntity;
        this.authorComponent = authorComponent;
        this.genreComponent = genreComponent;
        this.bookComponent = bookComponent;

        tabsToGrids();

        add(this.tabsEntity, this.authorComponent, this.genreComponent, this.bookComponent);
        setAlignItems(Alignment.CENTER);
    }


    private void tabsToGrids() {
        Map<Tab, Component> tabsToGrids = new HashMap<>();
        tabsToGrids.put(tabsEntity.getTab1(), authorComponent);
        tabsToGrids.put(tabsEntity.getTab2(), bookComponent);
        tabsToGrids.put(tabsEntity.getTab3(), genreComponent);

        tabsEntity.getTabs().addSelectedChangeListener(event -> {
            tabsToGrids.values().forEach(grid -> grid.setVisible(false));
            Component selectedPage = tabsToGrids.get(tabsEntity.getTabs().getSelectedTab());
            selectedPage.setVisible(true);
        });
    }
}
