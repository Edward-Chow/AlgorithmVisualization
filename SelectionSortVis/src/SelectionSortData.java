import java.util.Random;

public class SelectionSortData {

    private int[] numbers;
    //已排序元素中的最大索引
    public int orderedIndex = -1;
    //当前找到的最小元素的索引
    public int currentMinIndex = -1;
    //当前正在比较的元素的索引
    public int currentCompareIndex = -1;

    public SelectionSortData(int N, int randomBound) {
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
