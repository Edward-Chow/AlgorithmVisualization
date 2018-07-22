import java.util.Random;

public class HeapSortData {

    private int[] numbers;
    public int heapIndex; //numbers[heapIndex...N]已排序

    public HeapSortData(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++)
            numbers[i] = (int)(Math.random() * randomBound) + 1;
    }

    public int N() {
        return numbers.length;
    }

    public int get(int i) {
        if (i < 0 || i >= numbers.length)
            throw  new IllegalArgumentException("Invalid index to access SortData.");

        return numbers[i];
    }

    public void swap(int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
