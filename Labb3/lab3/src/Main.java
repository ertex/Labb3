import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("src\\elise.txt");
        Scanner sc = new Scanner(file);
        SoundDevice device = new SoundDevice();
        Song song = new Song(10);
        while(sc.hasNext()){
            song.add(MusicUtils.harmonic(sc.nextInt(),sc.nextDouble()));
        }
        song.play(device);
        song.save(device.getFormat(),new File("twotones.wav"));
    }//main


}//Main
