public class SelectionSort {


    public static void sort(Object[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int smallestIndex = 0;
            for (int j = i+1; j < arr.length; j++) {
                smallestIndex = arr[smallestIndex].hashCode() < arr[j].hashCode() ? smallestIndex : j;
            }
            Object o = arr[smallestIndex];
            arr[smallestIndex] = arr[i];
            arr[i] = o;
        }
    }
    
}
