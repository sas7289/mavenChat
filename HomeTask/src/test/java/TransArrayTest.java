

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TransArrayTest {
    private TransArray transArray;



    @DisplayName("Without 4")
    @Test
    public void testTransArr1() {
        Integer[] testArr = new Integer[] {1,2,3,5,6,7,8,9};

        Assertions.assertThrows(RuntimeException.class, () -> {
            TransArray.transArr(testArr);
        });
    }

    @DisplayName("4 in the middle")
    @Test
    public void testTransArr2() {
        Integer[] testArr = new Integer[] {1,2,3,4,5,6,7,8,9};
        Integer[] targetArr = new Integer[] {5,6,7,8,9};

        Assertions.assertArrayEquals(targetArr, TransArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            TransArray.transArr(testArr);
        });
    }

    @DisplayName("4 at the beginning adn at the end")
    @Test
    public void testTransArr3() {
        Integer[] testArr = new Integer[] {4,5,1,9,5,4,7,4};
        Integer[] targetArr = new Integer[]{};

        Assertions.assertArrayEquals(targetArr, TransArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            TransArray.transArr(testArr);
        });
    }

    @DisplayName("A few 4 in the middle")
    @Test
    public void testTransArr4() {
        Integer[] testArr = new Integer[] {1,2,3,4,6,4,7,3,12,3};
        Integer[] targetArr = new Integer[] {7,3,12,3};

        Assertions.assertArrayEquals(targetArr, TransArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            TransArray.transArr(testArr);
        });
    }


    @DisplayName("ParamTest")
    @ParameterizedTest
    @MethodSource("getArguments")
    public void testTransParam (Integer[] targetArr, Integer[] testArr) {
        Assertions.assertArrayEquals(targetArr, TransArray.transArr(testArr));
    }

    static Stream<Arguments> getArguments() {
        Integer[] testArr2 = new Integer[] {1,2,3,4,5,6,7,8,9};
        Integer[] testArr3 = new Integer[] {4,5,1,9,5,4,7,4};
        Integer[] testArr4 = new Integer[] {1,2,3,4,6,4,7,3,12,3};
        Integer[] targetArr2 = new Integer[] {5,6,7,8,9};
        Integer[] targetArr3 = new Integer[]{};
        Integer[] targetArr4 = new Integer[] {7,3,12,3};
        return Stream.of(
                Arguments.arguments(targetArr2, testArr2),
                Arguments.arguments(targetArr3, testArr3),
                Arguments.arguments(targetArr4, testArr4)
        );
    }
}