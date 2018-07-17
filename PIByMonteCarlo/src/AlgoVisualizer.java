import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private MonteCarloPiData piData;
    private AlgoFrame frame;    // 视图
    private int N;             //点数
    private static int DELAY = 10;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){


        if(sceneHeight != sceneHeight)
            throw new IllegalArgumentException("该例必须创建正方形窗口");
        // 初始化数据
        // TODO: 初始化数据

        Circle circle = new Circle(sceneWidth/2, sceneHeight/2, sceneHeight/2);
        piData = new MonteCarloPiData(circle);
        this.N = N;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get value of PI with Monte-Carlo method", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        // TODO: 编写自己的动画逻辑
        for (int i = 0; i < N; i++) {

            //执行100次输出一次
           if(i%100 == 0) {
               frame.render(piData);
               AlgoVisHelper.pause(DELAY);
               System.out.println(piData.estimatePi());
           }

            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            piData.addPoint(p);
        }
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 700;
        int sceneHeight = 700;
        int N = 10000;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
