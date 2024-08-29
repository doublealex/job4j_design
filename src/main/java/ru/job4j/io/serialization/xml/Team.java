package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class Team {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean exist;
    @XmlAttribute
    private int founded;
    private Manager manager;
    private String[] statistics;

    public Team() {
    }

    public Team(String name, boolean exist, int founded, Manager manager, String[] statistics) {
        this.name = name;
        this.exist = exist;
        this.founded = founded;
        this.manager = manager;
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Team{"
                + "name='"
                + name
                + '\''
                + ", exist="
                + exist
                + ", founded="
                + founded
                + ", manager="
                + manager
                + ", statistics="
                + Arrays.toString(statistics)
                + '}';
    }
}
