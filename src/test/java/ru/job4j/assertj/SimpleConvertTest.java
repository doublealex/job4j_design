package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).containsOnlyOnce("first")
                .isNotEmpty()
                .allSatisfy(e -> assertThat(e).hasSizeGreaterThanOrEqualTo(4))
                .anySatisfy(e ->
                        assertThat(e).containsSequence("ir"))
                .doesNotContain("six");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("olala", "jajaj", "nonono");
        assertThat(set).last().isNotNull()
                .hasSameClassAs("olala");
        assertThat(set).filteredOn(e -> e.length() > 5).first().isEqualTo("nonono");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "Alex", "second", "John", "third", "Marcus");
        assertThat(map).hasSize(6)
                .containsKeys("first", "Alex", "second", "John", "third", "Marcus")
                .containsValues(1, 2, 3, 4)
                .doesNotContainKey("Elon")
                .doesNotContainValue(54)
                .containsEntry("Alex", 1)
                .isNotEmpty();
    }
}