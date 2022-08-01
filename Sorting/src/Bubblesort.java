import java.util.stream.Stream;

public class Bubblesort {

    public static void sort(Object[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int i1 = 0; i1 < arr.length-1; i1++) {
                if(arr[i1].hashCode() > arr[i1+1].hashCode()) {
                    Object o = arr[i1 + 1];
                    arr[i1 + 1] = arr[i1];
                    arr[i1] = o;
                }
            }
        }
    }
}
