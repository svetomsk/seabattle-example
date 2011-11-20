package battlesea;

import java.io.*;
import java.util.Random;

public class Main {
        private static final int SIZE = 7; //const used fo size of the field
    public static void main(String[] args) throws IOException {
        int[][] field = new int[SIZE][SIZE]; //massive that is used for placing ships(1 is ship, 0 is empty)
        int k = 0; //int used for making fixed number of cycles
        int l = 0; //int used for making fixed number of cycles
        while (k < SIZE)//cycle that fills all the places with 0
        {
            while (l < SIZE) {
                field[l][k] = 0;
                l++;
            }
            l = 0;
            k++;
        }
        Random random = new Random();
        k = 0; //int used for making fixed number of cycles
        int s = 3; //quantity of 1x1(small) ships
        int m = 2; //quantity of 1x2(medium) ships
        int h = 1; //quantity of 1x3(huge) ships
        while (k < s)
        {
            int r1 = Math.abs(random.nextInt()) % (SIZE); //random int r1(heights) from 0 to SIZE - 1
            int r2 = Math.abs(random.nextInt()) % (SIZE); //random int r2(width) from 0 to SIZE - 1
            System.out.println(r1 + " " + r2);
            if (r1 != 0 && r2 != 0 && r1 != (SIZE - 1) && r2 != (SIZE - 1))
                {
                if (      field[r1][r2] != 1
                        &&field[r1 - 1][r2] != 1
                        &&field[r1][r2 - 1] != 1
                        &&field[r1 + 1][r2] != 1
                        &&field[r1][r2 + 1] != 1
                        &&field[r1 + 1][r2 + 1] != 1
                        &&field[r1 - 1][r2 - 1] != 1
                        &&field[r1 + 1][r2 - 1] != 1
                        &&field[r1 - 1][r2 + 1] != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            if (r1 == 0 && r2 != 0 && r2 != (SIZE - 1))
            {
                if (      field[r1][r2] != 1
                        &&field[r1][r2 - 1] != 1
                        &&field[r1][r2 + 1] != 1
                        &&field[r1 + 1][r2] != 1
                        &&field[r1 + 1][r2 + 1] != 1
                        &&field[r1 + 1][r2 - 1] != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }

            }
            if (r1 == (SIZE - 1) && r2 != 0 && r2 != (SIZE - 1))
                {
                if (      field[r1][r2] != 1
                        &&field[r1][r2 - 1] != 1
                        &&field[r1][r2 + 1] != 1
                        &&field[r1 - 1][r2] != 1
                        &&field[r1 - 1][r2 + 1] != 1
                        &&field[r1 - 1][r2 - 1] != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            if (r2 == 0 && r1 != 0 && r1 != (SIZE - 1))
            {
                if (      field[r2][r1] != 1
                        &&field[r2][r1 - 1]  != 1
                        &&field[r2][r1 + 1]  != 1
                        &&field[r2 + 1][r1]  != 1
                        &&field[r2 + 1][r1 + 1]  != 1
                        &&field[r2 + 1][r1 - 1]  != 1)
                 {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            
            if (r2 == (SIZE - 1) && r1 != 0 && r1 != (SIZE - 1))
            {
                if (      field[r2][r1]  != 1
                        &&field[r2][r1 - 1]  != 1
                        &&field[r2][r1 + 1]  != 1
                        &&field[r2 - 1][r1]  != 1
                        &&field[r2 - 1][r1 + 1]  != 1
                        &&field[r2 - 1][r1 - 1]  != 1)
                 {
                    field [r1][r2] = 1;
                     ++k;
                 }
                
            }
            if (r2 == 0 && r1 == 0)
            {
                if (      field[r1][r2]  != 1
                        &&field[r1 + 1][r2]  != 1
                        &&field[r1][r2 + 1]  != 1
                        &&field[r1 + 1][r2 + 1]  != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            if (r2 == 0 && r1 == (SIZE - 1))
            {
                if (      field[r1][r2]  != 1
                        &&field[r1 - 1][r2]  != 1
                        &&field[r1][r2 + 1]  != 1
                        &&field[r1 - 1][r2 + 1]  != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            if (r2 == (SIZE - 1) && r1 == 0)
            {
                 if (     field[r1][r2]  != 1
                        &&field[r1 + 1][r2]  != 1
                        &&field[r1][r2 - 1]  != 1
                        &&field[r1 + 1][r2 - 1]  != 1)
                {
                    field [r1][r2] = 1;
                    ++k;
                }
                
            }
            if (r2 == (SIZE - 1) && r1 == (SIZE - 1))
            {
                if (   field[r1][r2]  != 1
                        &&field[r1 - 1][r2]  != 1
                        &&field[r1][r2 - 1]  != 1
                        &&field[r1 - 1][r2 - 1] != 1)
                {
                     field [r1][r2] = 1;
                    ++k;
                }
               
            }



        }

        k = 0;//renewing k and l for new cycle that shows placed ships
        l = 0;
        System.out.println("###########---FIELD---###########");
        while (k < SIZE)
        {
            while (l < SIZE) {
                System.out.print(Integer.toString(field[l][k]) + " ");
                l++;
            }
            k++;
            l = 0;
            System.out.println(" ");

        }
    }
}
