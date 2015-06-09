package org.asi.ui.custom;

import org.asi.ui.custom.demo.ExtFilteringTableDemo;
import org.asi.ui.custom.demo.CustomWindowDemo;
import org.asi.ui.custom.demo.CustomTextFieldDemo;
import org.asi.ui.custom.demo.CustomCheckBoxDemo;
import org.asi.ui.custom.demo.CustomMenuBarDemo;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abhiram
 */
public class Custom extends VerticalLayout implements View{
    public static final String NAME="";
    public Custom(){
        init();
    }
    
    private void init() {
        Button x=new Button("Custom Window Demo");
        Button x1=new Button("Custom CheckBox Demo");
        Button y=new Button("Custom TextField Demo");
        Button z=new Button("Custom MenuBar Demo");
        Button a=new Button("Ext Filtering Table Demo");
        setSizeFull();
        addComponent(x);
        setComponentAlignment(x, Alignment.MIDDLE_CENTER);
        x.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(CustomWindowDemo.NAME);
            }
        });
        addComponent(x1);
        setComponentAlignment(x1, Alignment.MIDDLE_CENTER);
        x1.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(CustomCheckBoxDemo.NAME);
            }
        });
        addComponent(y);
        setComponentAlignment(y, Alignment.MIDDLE_CENTER);
        y.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                 getUI().getNavigator().navigateTo(CustomTextFieldDemo.NAME);
            }
        });
        addComponent(z);
        setComponentAlignment(z, Alignment.MIDDLE_CENTER);
        z.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(CustomMenuBarDemo.NAME);
            }
        });
        addComponent(a);
        setComponentAlignment(a, Alignment.MIDDLE_CENTER);
        a.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                getUI().getNavigator().navigateTo(ExtFilteringTableDemo.NAME);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
