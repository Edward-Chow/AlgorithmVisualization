import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
    private int maxTurn;
    private Board starterBorad;
    private int N, M;
    private Board showBoard;

    public GameData(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException("FileName cannot be null.");

        Scanner scanner = null;
        try {
            File file = new File(fileName);
            if (file == null)
                throw new IllegalArgumentException("File " + fileName + " is not exist.");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String turnLine = scanner.nextLine();
            maxTurn = Integer.parseInt(turnLine);

            ArrayList<String> lines = new ArrayList<String>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            starterBorad = new Board(lines.toArray(new String[lines.size()]));
            this.N = starterBorad.getN();
            this.M = starterBorad.getM();
            starterBorad.print();
            showBoard = new Board(starterBorad);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
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

    public Board getShowBoard() {
        return showBoard;
    }

    public boolean solve() {
        if (maxTurn < 0)
            return false;

        return solve(starterBorad, maxTurn);
    }

    private static int d[][] = {{-1, 0}, {0, 1}, {0,-1}};
    private boolean solve(Board board, int turn) {
        if(board == null)
            throw new IllegalArgumentException("board can not be null in solve function!");

        if(turn == 0)
            return board.isWin();

        if(board.isWin())
            return true;

        for (int x = 0; x < N; x++)
            for (int y = 0; y < M; y++) {
                if (board.getData(x, y) != Board.EMPTY) {
                    for (int i = 0; i < 3; i++) {
                        int newX = x+d[i][0];
                        int newY = y+d[i][1];
                        if (isInArea(newX, newY)) {
                            String swapString = String.format("swap (%d, %d) and (%d, %d)", x, y, newX, newY);
                            Board nextBoard = new Board(board, board, swapString);
                            nextBoard.swap(x, y, newX, newY);
                            nextBoard.run();
                            if (solve(nextBoard, turn-1))
                                return true;
                        }
                    }
                }
            }
        return false;
    }

}
