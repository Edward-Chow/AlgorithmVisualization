/***
 * 游戏中有种宝箱，打开宝箱中奖概率20%
 * 打开五个这样的宝箱，中奖概率是多少？
 * 用蒙特卡洛算法模拟。
 */
public class WinningPrize {

    private double chance;
    private int playTimes;
    private int N; //试验次数

    public WinningPrize(double chance, int playTimes, int n) {

        if (chance < 0.0 || chance > 1.0)
            throw new IllegalArgumentException("The value of chance must be between 0 and 1.");
        if (playTimes <= 0 || n <= 0)
            throw new IllegalArgumentException("The value of playTimes and N must be larger than 0.");

        this.chance = chance;
        this.playTimes = playTimes;
        N = n;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play())
                wins++;
        }

        System.out.println("winning rate: " + (double)wins/N);
    }

    public boolean play() {
        for (int i = 0; i < playTimes; i++)
            if (Math.random() < chance)
                return true;

        return false;
    }

    public static void main(String[] args) {
	// write your code here

        double chance = 0.2;
        int playTimes = 5;
        int N = 1000000; //试验次数

        WinningPrize winningPrize = new WinningPrize(chance, playTimes, N);
        winningPrize.run();
    }



}
