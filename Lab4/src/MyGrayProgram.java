public class MyGrayProgram { 
   public static void main(String[] args) throws Exception{ 
      int[][] original = GrayImage.read("mushroom.jpeg"); 
      int[][] manipulated = upDown(original); 
      GrayImage.write("upDownMushroom.jpeg", manipulated); 
      GrayImageWindow iw = new GrayImageWindow(original, manipulated); 
   }//main 

   public static int[][] upDown(int[][] samples) { 
      int[][] newSamples = new int[samples.length][samples[0].length]; 
      for (int row = 0; row < samples.length; row = row + 1) 
         for (int col = 0; col < samples[row].length; col = col + 1) 
            newSamples[row][col] = samples[samples.length - row - 1 ][col]; 
      return newSamples; 
    }//upDown 
}//MyGrayProgram
