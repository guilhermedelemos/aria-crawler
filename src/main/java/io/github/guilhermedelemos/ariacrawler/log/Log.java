package io.github.guilhermedelemos.ariacrawler.log;

import org.openqa.selenium.WebElement;

public class Log {

    public void log(String message) {
        System.out.println(message);
    }

    public void separator() {
        System.out.println("------------------------------");
    }

    public void logWebElement(WebElement element) {
        this.logWebElement(element, 0);
    }

    public void logWebElement(WebElement element, int level) {
        String indentation = "";
        for(int i=0;i<level*2;i++) {
            indentation += "--";
        }
        if(!indentation.isEmpty()) {
            indentation += ">";
        }
        this.log("-----");
        this.log(indentation + "id " + element.getAttribute("id"));
        this.log(indentation + "TagName " + element.getTagName());
        this.log(indentation + "Location X " + element.getLocation().getX());
        this.log(indentation + "Location Y " + element.getLocation().getY());
        this.log(indentation + "Height " + element.getSize().getHeight());
        this.log(indentation + "Width " + element.getSize().getWidth());
        this.log(indentation + "Displayed " + element.isDisplayed());
        this.log(indentation + "Enabled " + element.isEnabled());
        this.log(indentation + "Selected " + element.isSelected());
        this.log(indentation + "Text " + element.getText());
    }

}
