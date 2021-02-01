package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double roundeddown;

    public AcceleratingSawToothGenerator(int period, double roundeddown) {
        state = 0;
        this.period = period;
        this.roundeddown = roundeddown;
    }


    @Override
    public double next() {
        state = (state + 1) % period;
        if (state == 0) {
            this.period = (int) (this.period * roundeddown);
        }
        return normalize(state);
    }

    private double normalize(int state) {
        return 2.0 * state / (period - 1) - 1.0;
    }
}
