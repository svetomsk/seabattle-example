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
        int s = f1.getSmallShips();
        final int SIZE = f1.getSize();
        fillFieldByPlayer(f2, s, br, SIZE);
        System.out.println("");
        char[][] RField1 = new char[SIZE][SIZE];
        char[][] RField2 = new char[SIZE][SIZE];
        fillVisibleField(RField1);
        fillVisibleField(RField2);
        while(!victory)
        {
            makeTurn(f1, f2, br, RField1, RField2);
            victory = victoryCheck(f1, SIZE) || victoryCheck(f2, SIZE);
        }
        write("Victory!");
    }



    private static void fillVisibleField (char[][] field)
    {
        int size = field.length;
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                field[x][y] = '~';
            }
        }
    }

    private static void fillFieldByPlayer (Field f2, int s, BufferedReader br
            , int size)
    {
        int k = 0;//int used for making fixed number of cycles
        int x = 0;
        int y = 0;
        while(k < s)
        {
            try
            {
                write("Write the x, y coordinate of the cell you want"
                        + " to place a small ship into it");
                x = Integer.valueOf(br.readLine());
                y = Integer.valueOf(br.readLine());
                f2.setCell(x, y, 1);
                k++;
            }
            catch (IOException e)
            {
                write("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }
        }
    }

    private static void write (String s)
    {
        System.out.println(s);
    }

    private static void makeTurn (Field f1, Field f2, BufferedReader br
            , char[][] RField1, char[][] RField2)
    {
        boolean turn = false;
        int size = f1.getSize();
        try
        {
            write("Write the x, y coordinate of the cell you want"
                + " to destroy");
            while(!turn)
            {
                 int x = Integer.valueOf(br.readLine());
                 int y = Integer.valueOf(br.readLine());
                 if((f1.getCell(x, y) == 1) && (RField1[x][y] != 'X') &&
                         (RField1[x][y] != '0'))
                 {
                     f1.setCell(x, y, 0);
                     RField1[x][y] = 'X';
                     turn = true;
                 }
                if((f1.getCell(x, y) == 0) && (RField1[x][y] != 'X') &&
                         (RField1[x][y] != '0'))
                 {
                     RField1[x][y] = '0';
                     turn = true;
                 }
                 if((RField1[x][y] == 'X') && (RField1[x][y] == '0'))
                 {
                     write("Choose unknown cell");
                 }
                 printVisibleFields(RField1, RField2, size);

            }
            
        }
        catch(IOException e)
        {
            write("please write a number from 0 to "
                    + Integer.toString(size) + ", not anything else");
        }
        

    }
    private static void printVisibleFields(char[][] RField1, char[][]RField2,
            int size)
    {
        int k = 0;
        int l = 0;
        k = 0;
        l = 0;
        write("###########---RFIELD1---###########");
        while (k < size)
        {
            while (l < size)
            {
                System.out.print(RField1[k][l] + " ");
                l++;
            }
            k++;
            l = 0;
            write(" ");
        }
//        k = 0;
//        l = 0;
//        write("###########---RFIELD2---###########");
//        while (k < size)
//        {
//            while (l < size)
//            {
//                write(RField2[k][l] + " ");
//                l++;
//            }
//            k++;
//            l = 0;
//            write(" ");
//        }
    }
    private static boolean victoryCheck(Field f1, int size)
    {
        Integer[][] field = new Integer[size][size];
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                field[x][y] = f1.getCell(x, y);
            }
        }
        int k = field.length;
        for(int x = 0; x < k; x++)
        {
            for(int y = 0; y < k; y++)
            {
                if(field[x][y] == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

}