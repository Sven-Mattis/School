public class InsertionSort {


    public static void sort(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Object toSort = arr[i];
            for(int j = i; j > 0; j--) {
                arr[j] = arr[j-1];
                if(arr[j-1].hashCode() > toSort.hashCode()) {
                    arr[j] = toSort;
                    break;
                }
            }
        }
    }
}
