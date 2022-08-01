import java.util.Arrays;

public class TEST {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{9,8,7,6,5,4,3,2,1,0};
        InsertionSort.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

}
