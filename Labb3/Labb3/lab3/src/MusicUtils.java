import java.util.*;

public class MusicUtils {

    public static double[] sine(double freq, double duration) {
        int n = (int)(duration*SoundDevice.SAMPLING_RATE);
        double[] a = new double[n];
        double dx = 2*Math.PI*freq / SoundDevice.SAMPLING_RATE;
        for (int i = 0; i < n; i = i + 1) {
            a[i] = Math.sin(i * dx);
        }
        return a;
    }//sine

    public static double[] pluck(double freq, double duration){
        int n = (int)(duration*SoundDevice.SAMPLING_RATE);
        double[] a = new double[n];

        int p = (int)(SoundDevice.SAMPLING_RATE/freq);
        Random numgen = new Random();
        for(int i = 0; i < p ; i++) {
            a[i] = (numgen.nextDouble() * 2 - 1);
        }

        for(int j = p; j < n; j++){
            a[j] = (a[j-p] + a[j-(p-1)])*0.498;
        }
        return a;
    }//pluck

    public static double[] note(int pitch, double duration){
        double p = 440 * Math.pow(2.0, (pitch / 12.0));
        return pluck(p,duration);
    }

    public static double[] average(double[] t1, double[] t2){
        double[] a = new double[t1.length];
        for(int i = 0; i < t1.length; i++){
            a[i] = (t1[i] + t2[i])/2.0;
        }
        return a;
    }//average

    public static double[] harmonic(int pitch, double duration){
        double[] a = note(pitch , duration);
        double[] b = note(pitch - 12, duration);
        double[] c = note(pitch + 12, duration);
        double[] cb = average(c , b);
        double[] abc = average (cb, a);
        return abc;
    }
}
