public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;
    public char[][] maze;

    private int entranceX, entranceY;
    private int exitX, exitY;
    public boolean[][] visited;
    public boolean[][] inMist;

    public MazeData(int n, int m) {
        if(n%2 == 0 || m%2 == 0)
            throw new IllegalArgumentException("Our maze generation algorithm need the height and the width of the maze to be odd.");

        N = n;
        M = m;

        maze = new char[N][M];
        visited = new boolean[N][M];
        inMist = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i%2 == 1 && j%2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;

                inMist[i][j] = true;
            }
        }

        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = N - 1;
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
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

    public void openMist(int x, int y) {
        if (!isInArea(x, y))
            throw new IllegalArgumentException(" ");

        for (int i = x - 1; i <= x + 1; i++)
            for(int j = y - 1; j <= y + 1; j++)
                if (isInArea(x, y))
                    inMist[i][j] = false;

    }
}
