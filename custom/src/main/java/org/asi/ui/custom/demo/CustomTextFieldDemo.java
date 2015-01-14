/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.custom.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Abhiram
 */
public class CustomTextFieldDemo extends VerticalLayout implements View{
    public static final String NAME="customtextfield-demo";
    public CustomTextFieldDemo(){
        init();
    }
    
    private void init() {
        CustomTextField component = new CustomTextField();
        addComponent(component);
        final CustomWindow ob=new CustomWindow("Window");
        ob.setWidth("200px");
        ob.setHeight("200px");
        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        component.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                 UI.getCurrent().addWindow(ob);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
