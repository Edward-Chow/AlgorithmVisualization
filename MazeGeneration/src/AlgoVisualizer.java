import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图
    private static int blockside = 7;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public AlgoVisualizer(int N, int M){

        // 初始化数据
        // TODO: 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.getN() * blockside;
        int sceneWidth = data.getM() * blockside;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        setData(-1,-1);
        //go(data.getEntranceX(), data.getEntranceY()+1);
        //随机队列
        RandomQueue<Position> queue = new RandomQueue<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY()+1);
        queue.add(first);
        data.visited[first.getX()][first.getY()]  = true;
        data.openMist(first.getX(), first.getY());

        while (!queue.empty()) {
            Position curPos = queue.remove();
            for(int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.isInArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
        setData(-1,-1);

    }

    //递归 深度优先遍历
    private void go(int x, int y) {
        if (!data.isInArea(x, y))
            throw new IllegalArgumentException("x,y are not out of index in go function.");

        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0]*2;
            int newY = y + d[i][1]*2;
            if (data.isInArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x+d[i][0], y+d[i][1]);
                go(newX, newY);
            }
        }

    }

    private void setData(int x, int y) {
        if (data.isInArea(x, y))
            data.maze[x][y] = MazeData.ROAD;
        frame.render(data);
        AlgoVisHelper.pause(5);
    }


    public static void main(String[] args) {

        int N = 101;
        int M = 101;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M);
    }
}
