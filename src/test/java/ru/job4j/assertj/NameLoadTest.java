package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkContainsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String map = "name:ten";
        assertThatThrownBy(() -> nameLoad.parse(map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(map)
                .hasMessageContaining("'='")
                .hasMessageContaining("name:ten");
    }

    @Test
    void checkContainNoKey() {
        NameLoad nameLoad = new NameLoad();
        String map = "=j";
        assertThatThrownBy(() -> nameLoad.parse(map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =j does not contain a key")
                .hasMessageContaining(map);
    }
}