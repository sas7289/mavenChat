import java.util.Arrays;
import java.util.List;

public class TransArray {
    //    public static void main(String[] args) {
//        Integer[] arr = new Integer[] {1,2,3};
//        System.out.println(Arrays.toString(transArr(arr)));
//    }


    public Integer[] transArr(Integer[] originArr) {
        List<Integer> temp = Arrays.asList(originArr);
        int lastIndex = temp.lastIndexOf(4);
        if(lastIndex == -1) {
            throw new RuntimeException();
        }
        temp = temp.subList(++lastIndex, temp.size());
        return temp.toArray(new Integer[temp.size()]);
    }
}
