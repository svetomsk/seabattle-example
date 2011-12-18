package battlesea;

import java.io.*;
import java.util.Random;

public class Field
    {

     private static final int SIZE = 7; //const used fo size of the field

     private static int [][] neighbors;
     
     

     public int getSize ()
    {
         return SIZE;
    }

     public static void main(String[] args) throws IOException
     {
        initNeighbors();
        int[][] field1 = new int[SIZE][SIZE]; //massive that is used for placing ships(1 is ship, 0 is empty)
        int[][] field2 = new int[SIZE][SIZE];//~ for the 2nd player
        int k = 0; //int used for making fixed number of cycles
        int l = 0; //int used for making fixed number of cycles
        while (k < SIZE)//cycle that fills all the places with 0
        {
            while (l < SIZE) {
                field1[l][k] = 0;
                l++;
            }
            l = 0;
            k++;
        }


        Random random = new Random();
        k = 0; //int used for making fixed number of cycles
        int s1 = 3; //quantity of 1x1(small) ships
        int m1 = 2; //quantity of 1x2(medium) ships
        int h1 = 1; //quantity of 1x3(huge) ships
        int s2 = 3; //quantity of 1x1(small) ships
        int m2 = 2; //quantity of 1x2(medium) ships
        int h2 = 1; //quantity of 1x3(huge) ships
        while (k < s1)
        {
            int r1 = Math.abs(random.nextInt()) % (SIZE); //random int r1(heights) from 0 to SIZE - 1
            int r2 = Math.abs(random.nextInt()) % (SIZE); //random int r2(width) from 0 to SIZE - 1
            if ( areNeighborsFree(r1, r2, field1) == true)
            {
                if(field1[r1][r2] != 1)
                {
                    field1[r1][r2] = 1;
                    ++k;
                }
            }
        }
        k = 0;
        while (k < s2)
        {
            int r1 = Math.abs(random.nextInt()) % (SIZE); //random int r1(heights) from 0 to SIZE - 1
            int r2 = Math.abs(random.nextInt()) % (SIZE); //random int r2(width) from 0 to SIZE - 1
            if ( areNeighborsFree(r1, r2, field2) == true)
            {
                if(field2[r1][r2] != 1)
                {
                    field2[r1][r2] = 1;
                    ++k;
                }
            }
        }

        k = 0; //Unit test
        l = 0;
        System.out.println("###########---FIELD1---###########");
        while (k < SIZE)
        {
            while (l < SIZE) {
                System.out.print(Integer.toString(field1[l][k]) + " ");
                l++;
            }
            k++;
            l = 0;
            System.out.println(" ");

        }
        k = 0; //Unit test
        l = 0;
        System.out.println("###########---FIELD2---###########");
        while (k < SIZE)
        {
            while (l < SIZE) {
                System.out.print(Integer.toString(field2[l][k]) + " ");
                l++;
            }
            k++;
            l = 0;
            System.out.println(" ");

        }



    }

    static boolean areNeighborsFree (int row, int col, int [][] field)
    {
        for (int i = 0; i < 8; i++)
        {
            int dR = neighbors[i][0];
            int dC = neighbors[i][1];

            if(isValidCoord (row + dR, col + dC))
            {

                if(field[row + dR][col + dC] == 1)
                {
                    return false;
                }

            }
        }
        return true;
    }

    static boolean isValidCoord (int col, int row)
    {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private static void initNeighbors()
    {
        neighbors = new int [8][];
        neighbors [0] = new int [] {-1, -1};
        neighbors [1] = new int [] {-1, 0};
        neighbors [2] = new int [] {-1, 1};
        neighbors [3] = new int [] {0,  1};
        neighbors [4] = new int [] {1, 1};
        neighbors [5] = new int [] {1, 0};
        neighbors [6] = new int [] {1, -1};
        neighbors [7] = new int [] {0, -1};
     }
}
