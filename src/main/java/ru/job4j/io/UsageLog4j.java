package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Homer Simpson";
        int age = 40;
        char gender = 'M';
        double height = 5.10;
        float weight = 220.5F;
        long id = 298127935353L;
        byte kids = 2;
        short salary = 5000;
        boolean married = true;

        LOG.debug("INFO:\r\nname: {},age: {},gender {},height: {},weight: {}, id: {},kids: {},salary: {},married: {}",
                name, age, gender, height, weight, id, kids, salary, married);

        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}