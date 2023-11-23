package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alex K");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("this")).isEqualTo("something");
    }

    @Test
    void whenWrongFormat() {
        String path = "./data/when_wrong_format.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неверный формат ключа");
        }

    @Test
    void whenWrongFormat1() {
        String path = "./data/when_wrong_format1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неверный формат ключа");
    }

    @Test
    void whenWrongFormat2() {
        String path = "./data/when_wrong_format2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неверный формат ключа");
    }

    @Test
    void whenWrongFormatValue() {
        String path = "./data/when_wrong_format_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неверный формат значения");
    }

    @Test
    void whenManySeparatorSymbol() {
        String path = "./data/many_separator_symbols.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("this")).isEqualTo("something=");
        assertThat(config.value("that")).isEqualTo("something=1");
        }

    }

