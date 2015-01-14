/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.custom.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Abhiram
 */
public class CustomWindowDemo extends VerticalLayout implements View{
    public static final String NAME="customwindow-demo";
    static int i=1;
    public CustomWindowDemo(){
        init();
    }
    
    private void init() {

        // Initialize our new UI component
        final LookUp win = new LookUp("Abhiram");
        final LookUp win1 = new LookUp();
        Button x=new Button("First Window");
        Button y=new Button("Second Window");
        setSizeFull();
        
        addComponent(x);
        x.setDescription("Added minimize to tray");
        setComponentAlignment(x, Alignment.MIDDLE_CENTER);
        x.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(win.getParent()==null){
                    win.setClosable(false);
                    win.setMinimizeToTray(true);                    
                UI.getCurrent().addWindow(win);
                }else{
                    
                    LookUp win3 = new LookUp("Abhi"+(i++));
                    win3.setMinimizeToTray(true);
                    UI.getCurrent().addWindow(win3);
                }
            }
        });
        y.setDescription("Not Added minimize to tray");
        addComponent(y);
        setComponentAlignment(y, Alignment.MIDDLE_CENTER);
        y.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                 if(win1.getParent()==null){
                UI.getCurrent().addWindow(win1);
                }
            }
        });
    }
   
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
