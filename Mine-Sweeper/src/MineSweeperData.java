public class MineSweeperData {

    public static final String blockImageUrl = "resources/block.png";
    public static final String flagImageUrl = "resources/flag.png";
    public static final String mineImageUrl = "resources/mine.png";

    public static String numberImageUrl(int num) {
        if (num < 0 && num > 8)
            throw new IllegalArgumentException("No such a number image.");

        return "resources/" + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;
    private int[][] numbers;
    public boolean[][] open;
    public boolean[][] flag;


    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0)
            throw new IllegalArgumentException("Mize Sweeper size is invalid.");

        if (mineNumber < 0 || mineNumber > N * M)
            throw new IllegalArgumentException("Mine number is larger than the size of the mine-sweeper board!");

        this.N = N;
        this.M = M;
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flag = new boolean[N][M];
        numbers = new int[N][M];
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                numbers[i][j] = 0;

        generateMines(mineNumber);
        calculateNumbers();
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean isMine(int x, int y) {
        if (!isInArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function.");

        return mines[x][y];
    }

    public boolean isInArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private void generateMines(int num) {
        for (int i = 0; i < num; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }

        for (int i = N*M-1; i >= 0; i--) {
            int x1 = i / M;
            int y1 = i % M;

            int x2 = (int)(Math.random() * (i+1)) / M;
            int y2 = (int)(Math.random() * (i+1)) % M;

            swap(x1, y1, x2, y2);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    private void calculateNumbers( ) {
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if(mines[i][j])
                    numbers[i][j] = -1;

                numbers[i][j] = 0;
                for (int ii = i-1; ii <= i+1; ii++)
                    for (int jj = j-1; jj <= j+1; jj++)
                        if (isInArea(ii, jj) && mines[ii][jj])
                            numbers[i][j]++;
            }
    }

    public int getNumbers(int x, int y) {
        return numbers[x][y];
    }

    public void open(int x, int y) {
        if (!isInArea(x, y))
            throw new IllegalArgumentException("Out of index in open function!");

        if (isMine(x, y))
            throw new IllegalArgumentException("Cannot open a mine block.");

        open[x][y] = true;

        if (numbers[x][y] > 0)
            return;

        for (int i = x-1; i <= x+1; i++)
            for (int j = y-1; j <= y+1; j++)
                if (isInArea(i, j) && !open[i][j] && !isMine(i, j))
                    open(i, j);
    }
}
