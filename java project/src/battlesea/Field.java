package battlesea;

import java.io.*;
import java.util.Random;

public class Field
    {

     private static final int SIZE = 7; //const used for size of the field
     private static final int s = 1;    //quantity of 1x1(small) ships
     private static final int m = 1;    //quantity of 1x2(medium) ships
     private static final int b = 1;    //quantity of 1x3(big) ships
     private static final int h = 1;    //quantity of 1x4(huge) ships

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
     
     public int getMediumShips()
     {
         return m;
     }
     
     public int getBigShips()
     {
         return b;
     }
     
     public int getHugeShips()
     {
         return h;
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
        int k = 0; 
        int l = 0; 
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
        for(int i = 0; i < s; i++)
        {
            fillShipByRandom(random, field, 1);
        }
        for(int i = 0; i < m; i++)
        {
            fillShipByRandom(random, field, 2);
        }
        for(int i = 0; i < b; i++)
        {
            fillShipByRandom(random, field, 3);
        }
        for(int i = 0; i < h; i++)
        {
            fillShipByRandom(random, field, 4);
        }
    }

    
    private static void fillShipByRandom (Random random, int [][] field, int length)
    {
        boolean isSet = false;
        while (!isSet)
        {
            int x1= Math.abs(random.nextInt()) % (SIZE);
            int y1= Math.abs(random.nextInt()) % (SIZE);
            int x2= Math.abs(random.nextInt()) % (2) - 1;//choosing the direction of rotation
            int y2= Math.abs(random.nextInt()) % (2) - 1;
            if(areNeighborsFree(x1, y1, field) 
                && areNeighborsFree(x1+x2*length, y1+y2*length, field)
                && (y2==0||x2==0)&&!((x2==0) && (y2==0))
                && isValidCoord(x1+x2*length, y1+y2*length))
            {
                for (int l = 0; l < length; l++)
                {
                    field[x1 + x2*l][y1+y2*l] = 1;
                }
                isSet = true;
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
