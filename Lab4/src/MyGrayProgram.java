public class MyGrayProgram {
    public static void main(String[] args) throws Exception {
        int[][] original = GrayImage.read("mushroom.jpeg");
        int[][] manipulated = upDown(original);
        GrayImage.write("upDownMushroom.jpeg", manipulated);
        GrayImageWindow iw = new GrayImageWindow(original, manipulated);
    }//main


    public static int[][] upDown(int[][] samples) {
        int[][] newSamples = new int[samples.length][samples[0].length];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                newSamples[row][col] = samples[samples.length - row - 1][col];
        return newSamples;
    }//upDown

    public static int[][] leftRight(int[][] samples) {
        int[][] newSamples = new int[samples.length][samples[0].length];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                newSamples[row][col] = samples[row][samples[row].length - col - 1];
        return newSamples;
    }//LeftRght

    public static int[][] invert(int[][] samples) {
        int[][] newSamples = new int[samples.length][samples[0].length];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                newSamples[row][col] = 255 - samples[row][col];
        return newSamples;

    }//Invert

    public static int[][] toBlackWhite(int[][] samples) {
        int[][] newSamples = new int[samples.length][samples[0].length];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                if (samples[row][col] > 128) {
                    newSamples[row][col] = 255;
                } else {
                    newSamples[row][col] = 0;

                }
        return newSamples;

    }//toBlackWhite

    public static int[][] contour(int[][] samples) {
        int[][] blackWhite = toBlackWhite(samples);
        int[][] newSamples = new int[samples.length][samples[0].length];
        for (int row = 0; row < blackWhite.length; row = row + 1) {
            for (int col = 0; col < blackWhite[row].length; col = col + 1) {
                //makes sure it's not out of bounds
                if (row > 1 & row < (blackWhite.length - 1) & (col > 1 & col < ((blackWhite[row].length - 1)))) {
                    //checks for countour
                    if (blackWhite[row][col] == 255 &
                            (blackWhite[row + 1][col] == 0
                                    | blackWhite[row - 1][col] == 0
                                    | blackWhite[row][col + 1] == 0
                                    | blackWhite[row][col - 1] == 0)) {
                        newSamples[row][col] = 0;
                    } else {
                        newSamples[row][col] = 255;
                    }
                }
            }
        }
        return newSamples;
    }//countour
}//MyGrayProgram
