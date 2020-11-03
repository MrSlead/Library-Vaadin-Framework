package com.almod.component;

import com.almod.entity.Author;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class TabsEntity extends HorizontalLayout {
    private Tab tab1 = new Tab("Authors");
    private Tab tab2 = new Tab("Books");
    private Tab tab3 = new Tab("Genres");

    private Tabs tabs;

    public TabsEntity() {
        tabs = new Tabs(tab1, tab2, tab3);

        tabs.setSelectedTab(tab1);

        add(tabs);
    }

    public Tabs getTabs() {
        return tabs;
    }

    public Tab getTab1() {
        return tab1;
    }

    public Tab getTab2() {
        return tab2;
    }

    public Tab getTab3() {
        return tab3;
    }
}
