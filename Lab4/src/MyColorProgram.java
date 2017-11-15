public class MyColorProgram {
    public static void main(String[] args) throws Exception {
        int[][][] original = ColorImage.read("mushroom.jpeg");
        int[][][] manipulated = sobel(original);
        ColorImage.write("upDownMushroom.jpeg", manipulated);
        ColorImageWindow iw = new ColorImageWindow(original, manipulated);
    }//main

    public static int[][][] upDown(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][3];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                for (int c = 0; c < samples[row][col].length; c = c + 1)
                    newSamples[row][col][c] = samples[samples.length - row - 1][col][c];
        return newSamples;
    }//upDown

    public static int[][][] invert(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row = row + 1)
            for (int col = 0; col < samples[row].length; col = col + 1)
                for (int color = 0; color < samples[row][col].length; color = color + 1)
                    newSamples[row][col][color] = 255 - samples[row][col][color];
        return newSamples;

    }//Invert

    public static int[][][] toGrey(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row = row + 1) {
            for (int col = 0; col < samples[row].length; col = col + 1) {

                int luminosoty = (int) (
                        samples[row][col][0] * 0.299 +
                                samples[row][col][1] * 0.587 +
                                samples[row][col][2] * 0.114);
                for (int color = 0; color < 3; color++) {
                    newSamples[row][col][color] = luminosoty;

                }
            }
        }
        return newSamples;
    }//toGrey

    public static int[][][] blackWhite(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row = row + 1) {
            for (int col = 0; col < samples[row].length; col = col + 1) {

                int luminosoty = (int) (
                        samples[row][col][0] * 0.299 +
                                samples[row][col][1] * 0.587 +
                                samples[row][col][2] * 0.114);

                for (int color = 0; color < 3; color++) {
                    if (luminosoty > 128) {
                        newSamples[row][col][color] = 255;
                    } else {
                        newSamples[row][col][color] = 0;

                    }
                }
            }
        }
        return newSamples;
    }//BlackWhite

    public static int[][][] sharpenOne(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row++) {
            for (int col = 0; col < samples[row].length; col++) {

                for (int color = 0; color < samples[row][col].length; color++) {

                    newSamples[row][col][color] = 10 * samples[row][col][color];
                    //adding 10samples instead of 9 and then sumbtracting them again, o decreeese code volume
                    if (row > 0 && row < (samples.length - 1) && (col > 0 && col < ((samples[row].length - 1)))) {
                        //note that the for loops start at -1
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                newSamples[row][col][color] -= samples[row + i][col + j][color];
                                //subtracts the surrounding 8 pixels AND the pixel in [i][j]
                            }
                        }
                        if (newSamples[row][col][color] > 255) {
                            newSamples[row][col][color] = 255;
                        } else if (newSamples[row][col][color] < 0) {
                            newSamples[row][col][color] = 0;

                        }
                    }
                }
            }
        }
        return newSamples;

    }//sharpenOne

    public static int[][][] sharpenTwo(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row++) {
            for (int col = 0; col < samples[row].length; col++) {

                for (int color = 0; color < samples[row][col].length; color++) {

                    //makes sure that there is no indexoutofbounds
                    if (row > 0 && row < (samples.length - 1) && (col > 0 && col < ((samples[row].length - 1)))) {
                        newSamples[row][col][color] = 10 * samples[row][col][color];
                        //adding 10samples instead of 9 and then sumbtracting them again, o decreeese code volume

                        newSamples[row][col][color] = 5 * samples[row][col][color] - (
                                samples[row - 1][col][color] +
                                        samples[row + 1][col][color] +
                                        samples[row][col - 1][color] +
                                        samples[row][col + 1][color]);

                        //makes sure the color value is not >255 or <0 since that creates overflows
                        if (newSamples[row][col][color] > 255) {
                            newSamples[row][col][color] = 255;
                        } else if (newSamples[row][col][color] < 0) {
                            newSamples[row][col][color] = 0;

                        }
                    }
                }
            }
        }
        return newSamples;

    }//sharpenTwo

    public static int[][][] sobel(int[][][] samples) {
        int[][][] newSamples = new int[samples.length][samples[0].length][samples[0][0].length];
        for (int row = 0; row < samples.length; row++) {
            for (int col = 0; col < samples[row].length; col++) {
                for (int color = 0; color < samples[row][col].length; color++) {
                    //checking so the index is within bounds
                    if (row > 0 && row < (samples.length - 1) && (col > 0 && col < ((samples[row].length - 1)))) {
                        int dx = //changes on the x-axis
                                -samples[row - 1][col - 1][color]
                                        - 2 * samples[row - 1][col][color]
                                        - samples[row - 1][col + 1][color]
                                        + samples[row + 1][col - 1][color]
                                        + 2 * samples[row + 1][col][color]
                                        + samples[row + 1][col + 1][color];
                        int dy = //changes in the y-axis
                                -samples[row - 1][col - 1][color]
                                        - 2 * samples[row][col - 1][color]
                                        - samples[row + 1][col - 1][color]
                                        + samples[row - 1][col + 1][color]
                                        + 2 * samples[row][col + 1][color]
                                        + samples[row + 1][col + 1][color];
                        //allocates the *distance* between the
                        newSamples[row][col][color] = (int) Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));

                        //makes sure the color value is not >255 or <0 since that creates overflows
                        if (newSamples[row][col][color] > 255) {
                            newSamples[row][col][color] = 255;
                        } else if (newSamples[row][col][color] < 0) {
                            newSamples[row][col][color] = 0;

                        }

                    }
                }
            }
        }
        return newSamples;


    }//Sobel

}//MyColorProgram
