import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private FractalData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int maxDepth){

        // 初始化数据
        data = new FractalData(maxDepth);
        int sceneWidth = (int)Math.pow(3, maxDepth);
        int sceneHeight = (int)Math.pow(3, maxDepth);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {
        setData(data.depth);
    }

    private void setData(int depth) {
        if (depth >= 0)
            data.depth = depth;
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                int depth = e.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {
        AlgoVisualizer visualizer = new AlgoVisualizer(6);
    }
}
