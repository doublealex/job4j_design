package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        System.out.println();

        Team team = new Team("Arsenal", true, 1886, new Manager("Mikel Arteta"),
                new String[]{"vice-champion", "points:89"});
        JAXBContext context1 = JAXBContext.newInstance(Team.class);
        Marshaller marshaller1 = context1.createMarshaller();
        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml1;
        try (StringWriter writer = new StringWriter()) {
            marshaller1.marshal(team, writer);
            xml1 = writer.getBuffer().toString();
            System.out.println(xml1);
        }

        Unmarshaller unmarshaller1 = context1.createUnmarshaller();
        try (StringReader reader = new StringReader(xml1)) {
            Team result1 = (Team) unmarshaller1.unmarshal(reader);
            System.out.println(result1);
        }
    }
}