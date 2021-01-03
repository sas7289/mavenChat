

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransArrayTest {

    @DisplayName("Without 4")
    @Test
    public void testTransArr1() {
        Integer[] testArr = new Integer[] {1,2,3,5,6,7,8,9};
        TransArray transArray = new TransArray();

        Assertions.assertThrows(RuntimeException.class, () -> {
            transArray.transArr(testArr);
        });
    }

    @DisplayName("4 in the middle")
    @Test
    public void testTransArr2() {
        Integer[] testArr = new Integer[] {1,2,3,4,5,6,7,8,9};
        Integer[] targetArr = new Integer[] {5,6,7,8,9};
        TransArray transArray = new TransArray();

        Assertions.assertArrayEquals(targetArr, transArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            transArray.transArr(testArr);
        });
    }

    @DisplayName("4 at the beginning adn at the end")
    @Test
    public void testTransArr3() {
        Integer[] testArr = new Integer[] {4,5,1,9,5,4,7,4};
        Integer[] targetArr = new Integer[]{};
        TransArray transArray = new TransArray();

        Assertions.assertArrayEquals(targetArr, transArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            transArray.transArr(testArr);
        });
    }

    @DisplayName("A few 4 in the middle")
    @Test
    public void testTransArr4() {
        Integer[] testArr = new Integer[] {1,2,3,4,6,4,7,3,12,3};
        Integer[] targetArr = new Integer[] {7,3,12,3};
        TransArray transArray = new TransArray();

        Assertions.assertArrayEquals(targetArr, transArray.transArr(testArr));

        Assertions.assertDoesNotThrow(() -> {
            transArray.transArr(testArr);
        });
    }
}