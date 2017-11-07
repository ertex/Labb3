public class MyColorProgram { 
   public static void main(String[] args) throws Exception { 
      int[][][] original = ColorImage.read("pugpet.jpg");
      int[][][] manipulated = invert(original);
      ColorImage.write("upDownMushroom.jpeg", manipulated); 
      ColorImageWindow iw = new ColorImageWindow(original, manipulated); 
   }//main 

   public static int[][][] upDown(int[][][] samples) { 
      int[][][] newSamples = new int[samples.length][samples[0].length][3]; 
      for (int row = 0; row < samples.length; row = row + 1)
         for (int col = 0; col < samples[row].length; col = col + 1) 
            for (int c = 0; c < samples[row][col].length; c = c + 1) 
               newSamples[row][col][c] = samples[samples.length-row-1][col][c]; 
      return newSamples; 
   }//upDown

   public static int[][][] leftRight(int[][][] samples) {
      int[][][] newSamples = new int[samples.length][samples[0].length][3];
      for (int row = 0; row < samples.length; row = row + 1)
         for (int col = 0; col < samples[row].length; col = col + 1)
            for (int c = 0; c < samples[row][col].length; c = c + 1)
               newSamples[row][col][c] = samples[row][samples[row].length -col -1][c];
      return newSamples;
   }//leftRight

   public static int[][][] invert(int[][][] samples) {
      int[][][] newSamples = new int[samples.length][samples[0].length][3];
      for (int row = 0; row < samples.length; row = row + 1)
         for (int col = 0; col < samples[row].length; col = col + 1)
            for (int c = 0; c < samples[row][col].length; c = c + 1)
               newSamples[row][col][c] = 255 - samples[row][col][c];
      return newSamples;
   }//invert

   public static int[][] toGray(int[][][] samples) {
      int[][] newSamples = new int[samples.length][samples[0].length];
      for (int row = 0; row < samples.length; row = row + 1)
         for (int col = 0; col < samples[row].length; col = col + 1)
            int l = (int)(samples[row][col][0]*0.299 +
                    samples[row][col][1]*0.587 +
                    samples[row][col][2]*0.114);
            if(<128){
         newSamples[row][col]= 128;
            }else{
         newSamples[row][col]=
            }
      return newSamples;
   }//invert

}//MyColorProgram
