package fr.sacha.medoc.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;

public class Medicament {

    private StringProperty name;
    private StringProperty details;
    private IntegerProperty quantity;

    public Medicament(String name, String details, int quantity) {
        this.name = new SimpleStringProperty(name);
        this.details = new SimpleStringProperty(details);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public Medicament(){
        this.name = new SimpleStringProperty("Nom");
        this.details = new SimpleStringProperty("Details");
        this.quantity = new SimpleIntegerProperty(1);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDetails() {
        return details.get();
    }

    public void setDetails(String details) {
        this.details.set(details);
    }
}



