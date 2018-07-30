public class Board {
    private int N, M;
    private char[][] data;
    public static final char EMPTY = '.';
    private Board prevBoard = null;
    private String swapString = "";

    public Board(String[] lines) {
        if (lines == null)
            throw new IllegalArgumentException("Lines cannot be null in Board constructor.");

        N = lines.length;
        if (N == 0)
            throw new IllegalArgumentException("Lines cannot be empty in Board constructor.");
        M = lines[0].length();
        data = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                data[i][j] = lines[i].charAt(j);
        }
    }

    public Board(Board board, Board preBoard, String swapString) {
        if(board == null)
            throw new IllegalArgumentException("board can not be null in Board constructor!");

        this.N = board.N;
        this.M = board.M;
        this.data = new char[N][M];
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++)
                this.data[i][j] = board.data[i][j];
        this.prevBoard = preBoard;
        this.swapString = swapString;
    }

    public Board(Board board) {
        this(board, null, "");
    }


    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean isInArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        for(int i = 0 ; i < N ; i ++)
            System.out.println(String.valueOf(data[i]));
    }

    public char getData(int x, int y){
        if(!isInArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in getData!");

        return data[x][y];
    }

    public boolean isWin() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (data[i][j] != EMPTY)
                    return false;
        printSwapInfo();
        return true;
    }

    public void swap(int x1, int y1, int x2, int y2){

        if(!isInArea(x1, y1) || !isInArea(x2, y2))
            throw new IllegalArgumentException("x, y are out of index in swap!");

        char t = data[x1][y1];
        data[x1][y1] = data[x2][y2];
        data[x2][y2] = t;

        return;
    }

    public void run() {
        do {
            drop();
        } while (match());
        return;
    }

    private void drop() {
        for (int j = 0; j < M; j++) {
            int cur = N-1;
            for (int i = N-1; i >=0; i--) {
                if(data[i][j] != EMPTY) {
                    swap(i, j, cur, j);
                    cur--;
                }
            }
        }
    }

    private static int d[][] = {{0, 1}, {1, 0}};
    private boolean match() {
        boolean isMatched = false;
        boolean[][] tag = new boolean[N][M];
        for (int x = 0; x < N; x++)
            for(int y = 0; y < M; y++)
                if (data[x][y] != EMPTY) {
                    for (int i = 0; i < 2; i++) {
                        int newX1 = x+d[i][0];
                        int newY1 = y+d[i][1];
                        int newX2 = newX1+d[i][0];
                        int newY2 = newY1+d[i][1];
                        if (isInArea(newX1, newY1) && isInArea(newX2, newY2) &&
                                data[x][y] == data[newX1][newY1] && data[x][y] == data[newX2][newY2]) {
                            tag[x][y] = true;
                            tag[newX1][newY1] = true;
                            tag[newX2][newY2] = true;
                            isMatched = true;
                        }
                    }
                }
        for (int x = 0; x < N; x++)
            for (int y = 0; y < M; y++)
                if (tag[x][y])
                    data[x][y] = EMPTY;

        return isMatched;
    }

    private void printSwapInfo() {
        if (prevBoard != null)
            prevBoard.printSwapInfo();
        System.out.println(swapString);
    }

}
