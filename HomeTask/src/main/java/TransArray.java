import java.util.Arrays;
import java.util.List;

public class TransArray {


    public static Integer[] transArr(Integer[] originArr) {
        List<Integer> temp = Arrays.asList(originArr);
        int lastIndex = temp.lastIndexOf(4);
        if(lastIndex == -1) {
            throw new RuntimeException();
        }
        temp = temp.subList(++lastIndex, temp.size());
        return temp.toArray(new Integer[temp.size()]);
    }


}
