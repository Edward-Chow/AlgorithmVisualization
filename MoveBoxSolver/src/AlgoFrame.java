import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private GameData data;
    public void render(GameData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{
        private HashMap<Character, Color> colorMap;
        private ArrayList<Color> colorList;
        public AlgoCanvas(){
            // 双缓存
            super(true);
            colorMap = new HashMap<Character, Color>();
            colorList = new ArrayList<Color>();
            colorList.add(AlgoVisHelper.Red);
            colorList.add(AlgoVisHelper.Purple);
            colorList.add(AlgoVisHelper.Blue);
            colorList.add(AlgoVisHelper.Teal);
            colorList.add(AlgoVisHelper.LightGreen);
            colorList.add(AlgoVisHelper.Lime);
            colorList.add(AlgoVisHelper.Amber);
            colorList.add(AlgoVisHelper.DeepOrange);
            colorList.add(AlgoVisHelper.Brown);
            colorList.add(AlgoVisHelper.BlueGrey);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth/data.getM();
            int h = canvasHeight/data.getN();

            Board showBoard = data.getShowBoard();
            for(int i = 0 ; i < showBoard.getN() ; i ++)
                for(int j = 0 ; j < showBoard.getM() ; j++){
                    char c  = showBoard.getData(i, j);
                    if(c != Board.EMPTY){

                        if(!colorMap.containsKey(c)){
                            int sz = colorMap.size();
                            colorMap.put(c, colorList.get(sz));
                        }

                        Color color = colorMap.get(c);
                        AlgoVisHelper.setColor(g2d, color);
                        AlgoVisHelper.fillRectangle(g2d, j*h+2, i*w+2, w-4, h-4);

                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                        String text = String.format("(%d, %d)", i, j);
                        AlgoVisHelper.drawText(g2d, text, j*h+h/2, i*w+w/2);
                    }
                }


        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


