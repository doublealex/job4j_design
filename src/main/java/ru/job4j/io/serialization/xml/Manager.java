package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manager")
public class Manager {

    @XmlAttribute
    private String name;

    public Manager() {

    }

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manager: {"
                + name
                + '\''
                +
                '}';
    }
}
