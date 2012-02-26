package battlesea;

import java.io.*;
import java.util.Random;

public class Field
    {

     private static final int SIZE = 3; //const used for size of the field
     private static final int s = 1;    //quantity of 1x1(small) ships

     private static int [][] neighbors;

     private int[][] field;

     public Field()
     {
         field = new int[SIZE][SIZE];
         //massive that is used for placing ships(1 is ship, 0 is empty)
     }
     
     public boolean isValidCoord(int col, int row, int size)
     {
         return row >= 0 && row < size && col >= 0 && col < size;
     }
     
     public int[][] getField()
     {
         return field;
     }

     public int getSmallShips()
     {
         return s;
     }

     public int getSize()
     {
         return SIZE;
     }

     public void setCell(int row, int col, int value)
     {
         field[row][col] = value;
     }

     public int getCell(int row, int col)
     {
         int value = field[row][col];
         return value;
     }

     public void fillFieldByRandom() throws IOException
     {
        initNeighbors();
        int k = 0; //int used for making fixed number of cycles
        int l = 0; //int used for making fixed number of cycles
        while (k < SIZE)//cycle that fills all the places with 0
        {
            while (l < SIZE)
            {
                field[l][k] = 0;
                l++;
            }
            l = 0;
            k++;
        }
        Random random = new Random();
        k = 0; //int used for making fixed number of cycles
        while (k < s)
        {
            int r1 = Math.abs(random.nextInt()) % (SIZE); //random int r1(heights) from 0 to SIZE - 1
            int r2 = Math.abs(random.nextInt()) % (SIZE); //random int r2(width) from 0 to SIZE - 1
            if (areNeighborsFree(r1, r2, field) == true)
            {
                field[r1][r2] = 1;
                ++k;
            }
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
        neighbors = new int [9][];
        neighbors [0] = new int [] {-1, -1};
        neighbors [1] = new int [] {-1, 0};
        neighbors [2] = new int [] {-1, 1};
        neighbors [3] = new int [] {0,  1};
        neighbors [4] = new int [] {1, 1};
        neighbors [5] = new int [] {1, 0};
        neighbors [6] = new int [] {1, -1};
        neighbors [7] = new int [] {0, -1};
        neighbors [8] = new int [] {0, 0};
     }
}
