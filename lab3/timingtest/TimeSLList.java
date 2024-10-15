package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> N   = new AList<>();
        AList<Double> time = new AList<>();
        AList<Integer> opCount = new AList<>();
        int testLength = 1000;
        int M = 10000;

        for(int i = 0; i <= 5; i++) {
            SLList<Integer> Test = new SLList<>();
            for(int j = 1; j <= testLength; j++) {
                Test.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for(int k = 0; k < M; k++) {
                Test.getLast();
            }
            double testTime = sw.elapsedTime();
            N.addLast(testLength);
            opCount.addLast(M);
            time.addLast(testTime);
            testLength *= 2;
        }
        printTimingTable(N, time, opCount);

    }

}
