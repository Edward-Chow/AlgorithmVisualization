import java.awt.*;
import java.lang.management.MemoryManagerMXBean;

public class MonteCarloExperiment {

    private int squareSide;
    private int N;
    private int outputInterval = 1000;

    public void setOutputInterval(int outputInterval) {
        if(outputInterval <= 0 )
            throw new IllegalArgumentException("interval must larger than zero.");

        this.outputInterval = outputInterval;
    }

    public MonteCarloExperiment(int squareSide, int n) {

        if (squareSide <= 0 || n <= 0)
            throw new IllegalArgumentException("the value of squareSide and n must larger than zero.");

        this.squareSide = squareSide;

        N = n;
    }

    public void run() {
        Circle circle = new Circle(squareSide/2, squareSide/2, squareSide/2);
        MonteCarloPiData piData = new MonteCarloPiData(circle);

        for (int i = 0; i < N; i++) {

            if (i%outputInterval == 0) {
                System.out.println(piData.estimatePi());
            }

            int x = (int) (Math.random() * squareSide);
            int y = (int)(Math.random() * squareSide);
            piData.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
	// write your code here
        int squareSide = 700;
        int N = 1000000;

        MonteCarloExperiment experiment = new MonteCarloExperiment(squareSide, N);

        experiment.setOutputInterval(10000);
        experiment.run();
    }
}
