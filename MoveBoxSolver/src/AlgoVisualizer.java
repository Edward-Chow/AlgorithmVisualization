import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int blockside = 70;

    private GameData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(String fileName){

        // 初始化数据
        // TODO: 初始化数据
        data = new GameData(fileName);
        int sceneWidth = data.getM() * blockside;
        int sceneHeight = data.getN() * blockside;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver", sceneWidth, sceneHeight);
//            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData();
        if (data.solve())
            System.out.println("The game has a solution.");
        else
            System.out.println("The game doesn't have a solution.");
    }

    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(5);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

       String fileName = "level/boston_16.txt";

       AlgoVisualizer algoVisualizer = new AlgoVisualizer(fileName);
    }
}
