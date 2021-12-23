public class SignalPlotter {

    public static final double FIRST_LIMIT = -10;
    public static final double SECOND_LIMIT = 10;
    public static final int NUMBER_OF_POINTS = 1000;
    public static final int SAMPLING_RATE = 250;
    public static double firstLimit = 0;
    public static double secondLimit = 2200.0 / (double) SAMPLING_RATE; //Array restricted to length of 2200
    public static int numberOfPoints = (int) (secondLimit * SAMPLING_RATE);

    public static double[] createSamplingPoints(double firstLimit, double secondLimit, int numberOfPoints) {
        if (firstLimit == secondLimit) {
            numberOfPoints = 1;
        }
        double[] array = new double[numberOfPoints];
        double diff;
        for (int i = 0; i < numberOfPoints; i++) {
            if (numberOfPoints == 1) {
                array[i] = secondLimit;
            } else if (secondLimit < firstLimit) {
                diff = (firstLimit - secondLimit) / (numberOfPoints - 1);
                array[i] = firstLimit - i * diff;
            } else {
                diff = (secondLimit - firstLimit) / (numberOfPoints - 1);
                array[i] = firstLimit + i * diff;
            }
        }

        return array;
    }

    public static double sigmoid(double x) {
        double sig = 1 / (1 + Math.exp(-x));
        return sig;
    }

    public static double[] applySigmoidToArray(double[] xs) {
        double[] SigmoidArray = new double[xs.length];
        for (int i = 0; i < xs.length; i++) {
            SigmoidArray[i] = sigmoid(xs[i]);
        }

        return SigmoidArray;
    }

    public static void plotSigmoid() {
        double[] xs = createSamplingPoints(FIRST_LIMIT, SECOND_LIMIT, NUMBER_OF_POINTS);
        double[] ys = applySigmoidToArray(xs);
        PlotHelper.plot2D(xs, ys);
    }

    public static void plotEcg() {
        double[] ecgSignal = PlotHelper.readEcg("ecg.txt");
        double[] ecgTime = createSamplingPoints(firstLimit, secondLimit, numberOfPoints);
        //PlotHelper.plotEcg(ecgTime, ecgSignal);

        int[] idxRPeaks = PlotHelper.readPeaks("rpeaks.txt");
        double[] timeRPeaks = new double[idxRPeaks.length];
        for (int i = 0; i < idxRPeaks.length; i++) {
            timeRPeaks[i] = 1.0 / SAMPLING_RATE * idxRPeaks[i];
        }

        double[] rPeaks = new double[timeRPeaks.length]; // index arbeiten
        for (int i = 0; i < idxRPeaks.length; i++) {
            int index = idxRPeaks[i];
            rPeaks[i] = ecgSignal[index];
        }

        computeHeartRate(timeRPeaks);
        PlotHelper.plotEcg(ecgTime, ecgSignal, timeRPeaks, rPeaks);
    }

    public static void computeHeartRate(double[] array) {
        double[] bpm = new double[array.length - 1];
        System.out.println("Heart Rate:");
        for (int i = 0; i < array.length - 1; i++) {
            bpm[i] = (array[i + 1] - array[i]) * 60.0;
            System.out.println(String.format("%.2f", bpm[i]) + " bpm");
        }
    }

    public static void main(String[] args) {
        //printArray(createSamplingPoints(10, 4, 5));
        //printArray(applySigmoidToArray(createSamplingPoints(FIRST_LIMIT, SECOND_LIMIT, NUMBER_OF_POINTS)));
        plotSigmoid();
        plotEcg();
    }
}
