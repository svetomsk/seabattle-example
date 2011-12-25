package battlesea;

import java.io.*;
import java.util.Random;


public class Main
{
    public static void main(String[] args) throws IOException 
    {
        boolean victory = false;
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        Field f1 = new Field();
        f1.fillFieldByRandom();
        Field f2 = new Field();
        int k = 0;//int used for making fixed number of cycles
        int l = 0;//int used for making fixed number of cycles
        int s = f1.getSmallShips();
        int SIZE = f1.getSize();
        int x = 0;
        int y = 0;
        while(k < s)
        {
            System.out.println("Write the x, y coordinate of the cell you want"
                    + " to place a small ship in");
            x = Integer.valueOf(br.readLine());
            y = Integer.valueOf(br.readLine());
            f2.setCell(x, y, 1);
            k++;
        }
        System.out.println("");
        char[][] RField1 = new char[SIZE][SIZE];
        char[][] RField2 = new char[SIZE][SIZE];
        k = 0;
        l = 0;
        while (k < SIZE)//cycle that fills all the places with ~
        {
            while (l < SIZE) {
                RField1[l][k] = '~';
                RField2[l][k] = '~';
                l++;
            }
            l = 0;
            k++;
        }
        k = 0;
        l = 0;
        System.out.println("###########---FIELD---###########");
        while (k < SIZE)
        {
            while (l < SIZE)
            {
                System.out.print(RField1[l][k] + " ");
                l++;
            }
            k++;
            l = 0;
            System.out.println(" ");
        }
    }
    
}