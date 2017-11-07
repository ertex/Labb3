public class MyGrayProgram { 
   public static void main(String[] args) throws Exception{ 
      int[][] original = GrayImage.read("mushroom.jpeg");
      int[][] manipulated = contour(original);
      GrayImage.write("pugpet.jpeg", manipulated);
      GrayImageWindow iw = new GrayImageWindow(original, manipulated); 
   }//main 

   public static int[][] upDown(int[][] samples) { 
      int[][] newSamples = new int[samples.length][samples[0].length]; 
      for (int row = 0; row < samples.length; row = row + 1) 
         for (int col = 0; col < samples[row].length; col = col + 1) 
            newSamples[row][col] = samples[samples.length - row - 1 ][col]; 
      return newSamples; 
    }//upDown

   public static int[][] leftRight(int[][] samples){
       int[][] newSamples = new int[samples.length][samples[0].length];
       for(int row = 0; row < samples.length;row++){
           for(int col = 0; col< samples[row].length; col ++)
               newSamples[row][col]= samples[row][samples[row].length - col - 1];
       }
       return newSamples;
   }//leftRight

    public static int[][] invert(int[][] samples){
        int[][] newSamples = new int[samples.length][samples[0].length];
        for(int row = 0; row < samples.length;row++){
            for(int col = 0; col< samples[row].length; col ++)
                newSamples[row][col]= 255 - samples[row][col];
        }
        return newSamples;
    }//invert

    public static int[][] toBlackWhite(int[][] samples){
        int[][] newSamples = new int[samples.length][samples[0].length];
        for(int row = 0; row < samples.length;row++){
            for(int col = 0; col< samples[row].length; col ++) {
                if(samples[row][col]<128)
                    newSamples[row][col]= 0;
                else
                    newSamples[row][col]=255;
            }
        }
        return newSamples;
    }//toBlackWhite

    public static int[][] contour(int[][] samples){
        int[][] newSamples = new int[samples.length][samples[0].length];
        int[][] blackwhite = toBlackWhite(samples);

        for(int row = 0; row < samples.length;row++){
            for(int col = 0; col< samples[row].length; col ++) {
                if(row == 0 || col == 0 || row == samples.length-1 || col == samples[row].length-1){
                    newSamples[row][col] = 0;
                }else if(blackwhite[row][col] == 0){
                    boolean notblack = true;
                    int i = row -1;
                    int j = col -1;
                    while(notblack && i< row + 1){
                        while(notblack && j < col +1){
                            if(blackwhite[i][j] > 128){
                                notblack = false;
                            }
                            j++;
                        }
                        j = col - 1;
                        i++;
                    }

                    if(!notblack){
                        newSamples[row][col] = 0;
                    }else{
                        newSamples[row][col] = 255;
                    }
                }else{
                    newSamples[row][col] = 255;
                }
            }
        }
        return newSamples;
    }//toBlackWhite

}//MyGrayProgram
