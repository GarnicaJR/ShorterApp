package com.example.application.views.helloworld;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Shortener")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class HelloWorldView extends Div {

    private TextField name;
    private H1 mainTitle;


    public HelloWorldView() {
        setId("hello-world-view");
        
        mainTitle = new H1("Short URL");
        mainTitle.setClassName("main-title");
        add(mainTitle);
        
        Div customBorder = new Div();
        customBorder.setClassName("custom-border");
        
        
        Span subMessage = new Span("Paste the URL to be shortened");
        subMessage.setClassName("sub-message");
        
        
        TextField urlField = new TextField();
        urlField.setClassName("text-field");
        
        Button shortnerButton = new Button("Shorten URL");
        customBorder.add(subMessage, urlField, shortnerButton);
        
        Span customMessage = new Span("Shorter App is a free tool to shorten a URL or reduce a link.");
        
        customMessage.setClassName("custom-message");
        customBorder.add(customMessage);
        
        add(customBorder);
                
        
    }

}
