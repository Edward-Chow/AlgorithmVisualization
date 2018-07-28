import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private MineSweeperData data;        // 数据
    private AlgoFrame frame;    // 视图
    private static int blockside = 30;

    public AlgoVisualizer(int N, int M, int mineNumber){

        // 初始化数据
        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = data.getM() * blockside;
        int sceneHeight = data.getN() * blockside;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(false, -1, -1);
    }

    private void setData(boolean isLeftClicked, int x, int y) {
        if (data.isInArea(x, y)) {
            if (isLeftClicked) {
                if (data.isMine(x, y)) {
                    data.open[x][y] = true;
                } else {
                    data.open(x, y);
                }

            } else {
                data.flag[x][y] = !data.flag[x][y];
            }
        }
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e) {
            e.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );
            Point p = e.getPoint();
            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();

            int x = p.y / h;
            int y = p.x / w;

            if (SwingUtilities.isLeftMouseButton(e))
                setData(true, x, y);
            else
                setData(false, x, y);
        }
    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNum = 50;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(N, M, mineNum);
    }
}
