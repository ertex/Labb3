import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        double tempo = 148.0;//eitghnotes /min
        File file = new File("src\\elise.txt");
        Scanner sc = new Scanner(file);
        SoundDevice device = new SoundDevice();
        Song song = new Song(25);
        while (sc.hasNext()) {
            song.add(MusicUtils.harmonic(sc.nextInt(),parseTempo(sc.nextDouble(),tempo)));

        }
        song.play(device);
        song.save(device.getFormat(), new File("twotones.wav"));


    }//main

    public static double parseTempo(double duration, double tempo) {
        return (tempo/60) * duration;

    }

}//Main
