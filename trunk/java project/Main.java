package battlesea;

import java.io.*;
import java.util.Random;

public class Main 
{

    public static void main(String[] args) throws IOException 
    {
        int i = 7; //size of the field; supposed to be square
        int[][] field = new int[i][i]; //massive that is to be used for placing ships, 1 is ship, 0 is empty
        int k = 0; //int used for making fixed number of cycles
        int l = 0; //int used for making fixed number of cycles
        while (k < i)//cycle that fills all the places with 0
        {
            while (l < i) {
                field[l][k] = 0;
                l++;
            }
            k++;
        }
        Random random = new Random();
        k = 0; //int used for making fixed number of cycles
        int s = 3; //quantity of 1x1(small) ships
        int m = 2; //quantity of 1x2(medium) ships
        int h = 1; //quantity of 1x3(huge) ships
        while (k < s) //placing small ships in the field (the operation of placement is done s times)
        {
            int r1 = Math.abs(random.nextInt()) % 7; //random int r1(heights) from 0 to 6
            int r2 = Math.abs(random.nextInt()) % 7; //random int r2(width) from 0 to 6
            if (r2 == i && r1 == i) //cycle checking if there is already a ship at the angle [i][i]
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2 - 1][r1 - 1] == 1 || field[r2][r1 - 1] == 1 || field[r2 - 1][r1] == 1 || field[r2][r1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r2 == 0 && r1 == i) //cycle checking if there is already a ship at the angle [0][i]
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2 + 1][r1 - 1] == 1 || field[r2][r1 - 1] == 1 || field[r2 + 1][r1] == 1 || field[r2][r1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r2 == i && r1 == 0) //cycle checking if there is already a ship at the angle
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2 - 1][r1 + 1] == 1 || field[r2][r1 + 1] == 1 || field[r2 - 1][r1] == 1 || field[r2][r1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r2 == 0 && r1 == 0) //cycle checking if there is already a ship at the angle
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2 + 1][r1 + 1] == 1 || field[r2][r1 + 1] == 1 || field[r2 + 1][r1] == 1 || field[r2][r1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r2 == 0 && r1 != i && r1 != 0) //cycle checking if there is already a ship on the edge of the field
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2][r1] == 1 || field[r2][r1 + 1] == 1 || field[r2][r1 - 1] == 1 || field[r2 + 1][r1] == 1 || field[r2 + 1][r1 - 1] == 1 || field[r2 + 1][r1 + 1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r1 == 0 && r2 != i && r2 != 0) //cycle checking if there is already a ship on the edge of the field
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2][r1] == 1 || field[r2 + 1][r1] == 1 || field[r2 - 1][r1] == 1 || field[r2][r1 + 1] == 1 || field[r2 - 1][r1 + 1] == 1 || field[r2 + 1][r1 + 1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r2 == i && r1 != i && r1 != 0) //cycle checking if there is already a ship on the edge of the field
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2][r1] == 1 || field[r2][r1 + 1] == 1 || field[r2][r1 - 1] == 1 || field[r2 - 1][r1] == 1 || field[r2 - 1][r1 - 1] == 1 || field[r2 - 1][r1 + 1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
            if (r1 == i && r2 != i && r2 != 0) //cycle checking if there is already a ship on the edge of the field
            {
                //cycle checking if there is already a ship in 1 cell distance or at the place
                while (field[r2][r1] == 1 || field[r2 + 1][r1] == 1 || field[r2 - 1][r1] == 1 || field[r2][r1 - 1] == 1 || field[r2 - 1][r1 - 1] == 1 || field[r2 + 1][r1 - 1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }

            //cycle checking if there is already a ship not at the angle
            if (r2 != 0 && r1 != 0) {
                while (field[r2][r1] == 1 || field[r2][r1 - 1] == 1 || field[r2][r1 + 1] == 1 || field[r2 + 1][r1] == 1 || field[r2 + 1][r1 + 1] == 1 || field[r2][r1 - 1] == 1) {
                    r1 = Math.abs(random.nextInt()) % 7;
                    r2 = Math.abs(random.nextInt()) % 7;
                }
                field[r2][r1] = 1;
                k++;
            }
        }

        k = 0;//renewing k and l for new cycle that checks placed ships
        l = 0;
        while (k < i) {
            while (l < i) {
                System.out.print(Integer.toString(field[k][l]));
                l++;
            }
            k++;
            l = 0;
            System.out.println(" ");
        }
    }
}
// cycle for meduim ships
//while (k <= m)
//        {
//        int r1 = Math.abs(random.nextInt()) % 7; //random int r1(heights) from 0 to 6
//        int r2 = Math.abs(random.nextInt()) % 7; //random int r2(width) from 0 to 6
//        int r3 = Math.abs(random.nextInt()) % 2; //random int r3(direction) from 0 to 1; 1 is up-down
//        }

