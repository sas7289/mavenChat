import java.util.Arrays;
import java.util.List;

public class TransArray {


    public static Integer[] transArr(Integer[] originArr) {
        List<Integer> temp = Arrays.asList(originArr);
        int lastIndex = temp.lastIndexOf(4);
        if(lastIndex == -1 || temp.size() == 0) {
            throw new RuntimeException();
        }
        temp = temp.subList(++lastIndex, temp.size());
        return temp.toArray(new Integer[temp.size()]);
    }


    public static boolean isThereOneOrFour(int[] arr) {
        for (int i : arr) {
            if(i == 1 || i == 4) {
                return true;
            }
        }
        return false;
    }

}
