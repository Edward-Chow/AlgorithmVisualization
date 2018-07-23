import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int blockside = 7;//每个小方格的边长
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图

    private static final int d[][] =  {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//左下右上
    public AlgoVisualizer(String mazeFile){

        // 初始化数据
        // TODO: 初始化数据
        data = new MazeData(mazeFile);
        int sceneWidth = blockside * data.M();
        int sceneHeight = blockside * data.N();
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze-Solver-Visulization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        // TODO: 编写自己的动画逻辑
        setData(-1, -1, false);
        if(!go(data.getEntranceX(), data.getEntranceY()))
            System.out.println("The maze has no solution.");
        setData(-1, -1, false);
    }

    public boolean go(int X, int Y) {
        if(!data.isInArea(X, Y))
            throw new IllegalArgumentException("x and y is out of area in go.");

        data.visited[X][Y] = true;
        setData(X, Y, true);

        if (X == data.getExitX() && Y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = X + d[i][0];
            int newY = Y + d[i][1];
            if (data.isInArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY])
                if(go(newX, newY))
                    return true;
        }

        setData(X, Y, false);
        return false;
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    public void setData(int x, int y, boolean isPath) {
        if(data.isInArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    public static void main(String[] args) {

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer("maze_101_101.txt");
    }
}
