package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("c")
                .contains("ub")
                .doesNotContain("triangle")
                .startsWith("C")
                .startsWithIgnoringCase("cu")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(-1, 0);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .containsIgnoringCase("U")
                .contains("wn ob")
                .doesNotContain("bbb")
                .startsWith("U")
                .startsWithIgnoringCase("unk")
                .isEqualTo("Unknown object");
    }

    @Test
    void getCubeVertices() {
        Box box = new Box(8, 12);
        int quantity = box.getNumberOfVertices();
        assertThat(quantity).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(90)
                .isGreaterThanOrEqualTo(8)
                .isEqualTo(8);
    }

    @Test
    void getSphereVertices() {
        Box box = new Box(0, 10);
        int quantity = box.getNumberOfVertices();
        assertThat(quantity).isZero()
                .isGreaterThan(-1)
                .isLessThanOrEqualTo(0)
                .isEqualTo(0);
    }

    @Test
    void isCubeExist() {
        Box box = new Box(8, 12);
        boolean exist = box.isExist();
        assertThat(exist).isNotEqualTo(false)
                .isTrue()
                .isEqualTo(true);
    }

    @Test
    void isSomethingExist() {
        Box box = new Box(-1, 100);
        boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isNotEqualTo(true)
                .isNotNull()
                .isEqualTo(false);
    }

    @Test
    void getCubeArea() {
        Box box = new Box(8, 12);
        double area = box.getArea();
        assertThat(area).isCloseTo(865d, withPrecision(1.0d))
                .isGreaterThan(100d)
                .isLessThan(1000d)
                .isEqualTo(864);
    }

    @Test
    void getSphereArea() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(525d)
                .isLessThan(5000d)
                .isEqualTo(4 * Math.PI * (10 * 10))
                .isBetween(1000d, 1300d)
                .isNotEqualTo(50.0)
                .isNotEqualByComparingTo(1256d)
                .isCloseTo(1256d, Percentage.withPercentage(1.0));
    }
}