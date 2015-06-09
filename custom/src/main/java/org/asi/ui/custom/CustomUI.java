package org.asi.ui.custom;

import com.vaadin.annotations.Theme;
import org.asi.ui.custom.demo.ExtFilteringTableDemo;
import org.asi.ui.custom.demo.CustomWindowDemo;
import org.asi.ui.custom.demo.CustomTextFieldDemo;
import org.asi.ui.custom.demo.CustomCheckBoxDemo;
import org.asi.ui.custom.demo.CustomMenuBarDemo;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@Theme("customTheme")
public class CustomUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = CustomUI.class, widgetset = "org.asi.ui.custom.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    Navigator navigator;
    @Override
    protected void init(VaadinRequest request) {
        
        navigator=new Navigator(this, this);
        navigator.addView(Custom.NAME, new Custom());
        navigator.addView(CustomWindowDemo.NAME, new CustomWindowDemo());
        navigator.addView(CustomCheckBoxDemo.NAME, new CustomCheckBoxDemo());
        navigator.addView(CustomTextFieldDemo.NAME, new CustomTextFieldDemo());
        navigator.addView(CustomMenuBarDemo.NAME, new CustomMenuBarDemo());
        navigator.addView(ExtFilteringTableDemo.NAME, new ExtFilteringTableDemo());
        
    }

}
