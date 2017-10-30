import java.io.File;

public class Main {

    public static double[] sine(double freq, double duration) {
        int n = (int)(duration*SoundDevice.SAMPLING_RATE);
        double[] a = new double[n];
        double dx = 2*Math.PI*freq / SoundDevice.SAMPLING_RATE;
        for (int i = 0; i < n; i = i + 1) {
            a[i] = Math.sin(i * dx);
        }
        return a;
    }//sine
    
    
    public static void main(String[] args) {
        SoundDevice device = new SoundDevice();
        Song song = new Song(5);
        song.add(sine(440,2));
        song.add(sine(880,2));
        song.play(device);
        song.save(device.getFormat(),new File("twotones.wav"));
    }//main 
}//Main
