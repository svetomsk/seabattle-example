package battlesea;

import java.io.*;
import java.util.Random;


public class Main
{
    public static void main(String[] args) throws IOException 
    {
        boolean victory = false;
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
 /*     char[][] RField1 = new char[SIZE][SIZE];
        char[][] RField2 = new char[SIZE][SIZE];
        Field f1 = new Field();
        Field f2 = new Field();
       while (k < SIZE)//cycle that fills all the places with ~
        {
            while (l < SIZE) {
                RField1[l][k] = '~';
                l++;
            }
            l = 0;
            k++;
        }
        while (k < SIZE)//cycle that fills all the places with ~
        {
            while (l < SIZE) {
                RField2[l][k] = '~';
                l++;
            }
            l = 0;
            k++;
        }
 */
        String decision = br.readLine();
        String[] coordinates = decision.split(" ");
        int x = Integer.valueOf(coordinates[0]);
        int y = Integer.valueOf(coordinates[1]);
    }
    
}