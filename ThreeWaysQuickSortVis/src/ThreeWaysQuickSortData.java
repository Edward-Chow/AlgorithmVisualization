import java.util.Random;

public class ThreeWaysQuickSortData {

    private int[] numbers;
    public int l, r;
    public int currenPivot;
    public int currentL, currentR;
    public boolean[] fixedPivot;
    public ThreeWaysQuickSortData(int N, int randomBound) {
        numbers = new int[N];
        fixedPivot = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
            fixedPivot[i] = false;
        }
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
        if(i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access SortData.");

        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
