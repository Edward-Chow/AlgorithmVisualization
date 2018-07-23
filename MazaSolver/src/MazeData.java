import java.io.*;
import java.util.Scanner;

public class MazeData {
    private int N, M;
    private char[][] maze;

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int entranceX, entranceY;
    private int exitX, exitY;
    public boolean[][] visited;
    public boolean[][] path;
    public MazeData(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("Filename cannot be null");
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File " + filename + " does not exist.");
            FileInputStream fileInputStream = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");

            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");

            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                if (line.length() != M)
                    throw new IllegalArgumentException("Maze file " + filename + " is invalid.");
                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public int N() {return N;}
    public int M() {return M;}

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

    public char getMaze(int i, int j) {
        if(!isInArea(i, j))
            throw new IllegalArgumentException("i and j is out of bounds in getMaze.");

        return maze[i][j];
    }
    public boolean isInArea(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }
    public void print() {
        System.out.println(N + " " + M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
