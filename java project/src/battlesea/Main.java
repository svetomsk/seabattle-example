package battlesea;

import java.io.*;
import java.util.Random;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        boolean victory = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Field f1 = new Field();
        Field f2 = new Field();
        f1.fillFieldByRandom();
        fillFieldByPlayer(f2, br);
        writeln(" ");
        final int SIZE = f1.getSize();
        Random random = new Random();
        writeln("Your field:");
        printInvisibleFields(f2);
        while (!victory)
        {
            makePlayerTurn(f1, br);
            makeComputerTurn(f2, random);
            printVisibleField(f1);
            printVisibleField(f2);
            victory = (victoryCheck(f1, SIZE) || victoryCheck(f2, SIZE));
        }
        writeWhoIsWinner(f1, f2);
    }

    private static void writeln(String s)
    {
        System.out.println(s);
    }

    private static void write(String s)
    {
        System.out.print(s);
    }

    private static int[][] transferFieldIntoIntegers(Field f1)
    {
        int size = f1.getSize();
        int[][] Field = new int[size][size];
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                Field[x][y] = f1.getCell(x, y);
            }
        }
        return Field;
    }
    

    private static void fillFieldByPlayer(Field f2, BufferedReader br)
    {
        int s = f2.getSmallShips();
        int m = f2.getMediumShips();
        int b = f2.getBigShips();
        int h = f2.getHugeShips();
        int size = f2.getSize();
        int[][] Field = transferFieldIntoIntegers(f2);
        for(int i = 0; i < s; i++)
        {
            writeln("<small>");
            fillFieldWithShipsByPlayer(f2, br, size, 0, Field);
        }
        for(int i = 0; i < m; i++)
        {
            writeln("<medium>");
            fillFieldWithShipsByPlayer(f2, br, size, 1, Field);
        }
        for(int i = 0; i < b; i++)
        {
            writeln("<big>");
            fillFieldWithShipsByPlayer(f2, br, size, 2, Field);
        }
        for(int i = 0; i < h; i++)
        {
            writeln("<huge>");
            fillFieldWithShipsByPlayer(f2, br, size, 3, Field);
        }
    }

    private static void fillFieldWithShipsByPlayer(Field f2, 
            BufferedReader br, int size, int l, int[][] Field)
    {
        for (int k = 0; k < 1;)
        {
            try {
                writeln("Write the x1, y1, x2, y2 coordinates of the cell you want"
                        + " to place a ship between");
                int x1 = Integer.valueOf(br.readLine());
                int y1 = Integer.valueOf(br.readLine());
                int x2 = Integer.valueOf(br.readLine());
                int y2 = Integer.valueOf(br.readLine());
                if (((x1 == x2) || (y1 == y2))
                        && canPlaceShipHere(x1, y1, x2, y2, Field)
                        && ((Math.abs(x1-x2) >= l) || (Math.abs(y1-y2) >= l)))

                {
                    for(int i = x1; i <= x2; i++)
                    {
                        f2.setCell(i, y1, 1);
                        k++;
                    }
                    for(int i = x2; i <= x1; i++)
                    {
                        f2.setCell(i, y1, 1);
                        k++;
                    }
                    for(int i = y1; i <= y2; i++)
                    {
                        f2.setCell(x1, i, 1);
                        k++;
                    }
                    for(int i = y2; i <= y1; i++)
                    {
                        f2.setCell(x1, i, 1);
                        k++;
                    }
                    
                }
                else
                {
                    writeln("The ship is being placed incorrectly");
                }
            } 
            catch (IOException e)
            {
                writeln("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }

        }
    }

    public static boolean canPlaceShipHere (int x1, int y1, int x2, int y2, 
            int[][] field)
    {
        int l = field[0].length;
        if((x1 < l) && (y1 < l) && (x2 < l) && (y2 < l))
        {
            for(int i = x1; i <= x2; i++)
            {
                if(!Field.areNeighborsFree(i, y1, field))
                {
                    return false;
                }
            }
            for(int i = x2; i <= x1; i++)
            {
                if(!Field.areNeighborsFree(i, y1, field))
                {
                    return false;
                }
            }
            for(int i = y1; i <= y2; i++)
            {
                if(!Field.areNeighborsFree(x1, i, field))
                {
                    return false;
                }
            }
            for(int i = y2; i <= y1; i++)
            {
                if(!Field.areNeighborsFree(x1, i, field))
                {
                    return false;
                }
            }
            if(!Field.areNeighborsFree(x1, y2, field))
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        return true;        
    }
    
    private static void makePlayerTurn(Field f1, BufferedReader br)
    {
        boolean turn = false;
        int size = f1.getSize();
        try {
            writeln("Write the x, y coordinate of the cell you want"
                    + " to destroy");
            while (!turn)
            {
                int x = Integer.valueOf(br.readLine());
                int y = Integer.valueOf(br.readLine());
                if (f1.getCell(x, y) == 1)
                {
                    f1.setCell(x, y, 3);
                    if(shipIsDestroyed(x, y, f1))
                    {
                        destroyShipSurroundings(x, y, f1);
                    }
                    writeln("Ship hit");
                    printVisibleField(f1);
                }
                if (f1.getCell(x, y) == 0)
                {
                    f1.setCell(x, y, 2);
                    turn = true;
                }
                if (!turn)
                {
                    writeln("Choose an undestroyed cell");
                }
            }
        } 
        catch (IOException e)
        {
            writeln("Please write a number from 0 to "
                    + Integer.toString(size) + ", not anything else");
        }
    }
    
    private static void destroyShipSurroundings(int x, int y, Field f1)
    {
        int size = f1.getSize();
        while(f1.isValidCoord(x+1, y, size)
                                && f1.getCell(x+1, y) == 3)
        {
            x++;
            if(f1.isValidCoord(x, y+1, size))
            {
                f1.setCell(x, y+1, 2);
            }
            if(f1.isValidCoord(x, y-1, size))
            {
                f1.setCell(x, y-1, 2);
            }
        }
        if(f1.isValidCoord(x+1, y+1, size))
        {
            f1.setCell(x+1, y+1, 2);
        }
        if(f1.isValidCoord(x+1, y-1, size))
        {
            f1.setCell(x+1, y-1, 2);
        }
        if(f1.isValidCoord(x+1, y, size))
        {
            f1.setCell(x+1, y, 2);
        }
        while(f1.isValidCoord(x-1, y, size)
                && f1.getCell(x-1, y) == 3)
        {                            
            x--;
            if(f1.isValidCoord(x, y+1, size))
            {
                f1.setCell(x, y+1, 2);
            }
            if(f1.isValidCoord(x, y-1, size))
            {
                f1.setCell(x, y-1, 2);
            }
        }
        if(f1.isValidCoord(x-1, y+1, size))
        {
            f1.setCell(x-1, y+1, 2);
        }
        if(f1.isValidCoord(x-1, y-1, size))
        {
            f1.setCell(x-1, y-1, 2);
        }
        if(f1.isValidCoord(x-1, y, size))
        {
            f1.setCell(x-1, y, 2);
        }
        while(f1.isValidCoord(x, y+1, size)
                && f1.getCell(x, y+1) == 3)
        {                            
            y++;
            if(f1.isValidCoord(x+1, y, size))
            {
                f1.setCell(x+1, y, 2);
            }
            if(f1.isValidCoord(x-1, y, size))
            {
                f1.setCell(x-1, y, 2);
            }
        }
        if(f1.isValidCoord(x+1, y+1, size))
        {
            f1.setCell(x+1, y+1, 2);
        }
        if(f1.isValidCoord(x-1, y+1, size))
        {
            f1.setCell(x-1, y+1, 2);
        }
        if(f1.isValidCoord(x, y+1, size))
        {
            f1.setCell(x, y+1, 2);
        }
        while(f1.isValidCoord(x, y-1, size)
                && f1.getCell(x, y-1) == 3)
        {                            
            y--;
            if(f1.isValidCoord(x+1, y, size))
            {
                f1.setCell(x+1, y, 2);
            }
            if(f1.isValidCoord(x-1, y, size))
            {
                f1.setCell(x-1, y, 2);
            }
        }
        if(f1.isValidCoord(x+1, y-1, size))
        {
            f1.setCell(x+1, y-1, 2);
        }
        if(f1.isValidCoord(x-1, y-1, size))
        {
            f1.setCell(x-1, y-1, 2);
        }
        if(f1.isValidCoord(x, y-1, size))
        {
            f1.setCell(x, y-1, 2);
        }
    }
    
    private static boolean shipIsDestroyed(int x, int y, Field f1)
    {
        int size = f1.getSize();
        while((f1.isValidCoord(x+1, y, size)) 
                && (f1.getCell(x+1, y) != 0)
                && (f1.getCell(x+1, y) != 2))
        {
            x++;
            if(f1.getCell(x, y) == 1)
            {
                return false;
            }
        }
        while((f1.isValidCoord(x-1, y, size)) 
                && (f1.getCell(x-1, y) != 0)
                && (f1.getCell(x-1, y) != 2))
        {
            x--;
            if(f1.getCell(x, y) == 1)
            {
                return false;
            }
        }
        while((f1.isValidCoord(x, y-1, size))
                && (f1.getCell(x, y-1) != 0)
                && (f1.getCell(x, y-1) != 2))
        {
            y--;
            if(f1.getCell(x, y) == 1)
            {
                return false;
            }
        }
        while((f1.isValidCoord(x, y+1, size)) 
                && (f1.getCell(x, y+1) != 0)
                && (f1.getCell(x, y+1) != 2))
        {
            y++;
            if(f1.getCell(x, y) == 1)
            {
                return false;
            }
        }
        return true;
        
    }

    private static void makeComputerTurn(Field f2, Random random)
    {
        int size = f2.getSize();
        boolean turn = false;
        while (!turn && (almostDestroyedShips(f2)[0] == -1)) //randoming cells, no choise
        {
            int r1 = Math.abs(random.nextInt()) % (size);
            int r2 = Math.abs(random.nextInt()) % (size);
            if (f2.isValidCoord(r1, r2, size))
            {
                if (f2.getCell(r1, r2) == 1)
                {
                    f2.setCell(r1, r2, 3);
                    if(shipIsDestroyed(r1, r2, f2))
                    {
                        destroyShipSurroundings(r1, r2, f2);
                    }
                    writeln("Ship hit or destroyed");
                    printVisibleField(f2);
                }
                if (f2.getCell(r1, r2) == 0)
                {
                    f2.setCell(r1, r2, 2);
                    turn = true;
                }
            }
        }
        while(!turn && (almostDestroyedShips(f2)[0] != -1)) //acting like primitive AI
        {
            int x = almostDestroyedShips(f2)[0];
            int y = almostDestroyedShips(f2)[1];
            if((f2.isValidCoord(x+1, y, size)) && f2.getCell(x+1, y) == 1)
            {
                if((f2.isValidCoord(x-1, y, size)) && f2.getCell(x-1, y) == 0)
                {
                    x = x-1;
                    if (f2.getCell(x, y) == 1)
                    {
                        f2.setCell(x, y, 3);
                        if(shipIsDestroyed(x, y, f2))
                        {
                            destroyShipSurroundings(x, y, f2);
                        }
                        writeln("Ship hit or destroyed");
                        printVisibleField(f2);
                    }
                    if (f2.getCell(x, y) == 0)
                    {
                        f2.setCell(x, y, 2);
                        turn = true;
                    }
                }
                else
                {
                    while((f2.isValidCoord(x+1, y, size)) && f2.getCell(x+1, y) == 1)
                    {
                        x++;
                    }
                    x++;
                    if (f2.getCell(x, y) == 1)
                    {
                        f2.setCell(x, y, 3);
                        if(shipIsDestroyed(x, y, f2))
                        {
                            destroyShipSurroundings(x, y, f2);
                        }
                        writeln("Ship hit or destroyed");
                        printVisibleField(f2);
                    }
                    if (f2.getCell(x, y) == 0)
                    {
                        f2.setCell(x, y, 2);
                        turn = true;
                    }
                }
            }
            else
            {
                int r1 = Math.abs(random.nextInt()) % (3)-1;
                int r2 = Math.abs(random.nextInt()) % (3)-1;
                if((f2.isValidCoord(x+r1, y+r2, size))
                        &&((r1==0)||(r2==0))&&!((r1==0)&&(r2==0)))
                {
                    x = x+r1;
                    y = y+r2;
                    if (f2.getCell(x, y) == 1)
                    {
                        f2.setCell(x, y, 3);
                        if(shipIsDestroyed(x, y, f2))
                        {
                            destroyShipSurroundings(x, y, f2);
                        }
                        writeln("Ship hit or destroyed");
                        printVisibleField(f2);
                    }
                    if (f2.getCell(x, y) == 0)
                    {
                        f2.setCell(x, y, 2);
                        turn = true;
                    }
                }
            }
        }
    }

    private static void printVisibleField(Field f1)
    {
        int size = f1.getSize();
        for (int k = 0; k < size; k++)
        {
            for (int l = 0; l < size; l++)
            {
                if ((f1.getCell(k, l) == 1) || (f1.getCell(k, l) == 0))
                {
                    write("~ ");
                }
                if (f1.getCell(k, l) == 3)
                {
                    write("X ");
                }
                if (f1.getCell(k, l) == 2)
                {
                    write("0 ");
                }
            }
            writeln(" ");
        }
        writeln(" ");
    }

    private static void printInvisibleFields(Field f1)
    {
        int size = f1.getSize();
        for (int k = 0; k < size; k++)
        {
            for (int l = 0; l < size; l++)
            {
                if (f1.getCell(k, l) == 1)
                {
                    write("S ");
                }
                if (f1.getCell(k, l) == 0)
                {
                    write("~ ");
                }
                if (f1.getCell(k, l) == 3)
                {
                    write("X ");
                }
                if (f1.getCell(k, l) == 2)
                {
                    write("0 ");
                }
            }
            writeln(" ");
        }
        writeln(" ");
    }
    
    private static int[] almostDestroyedShips(Field f1)
    {
        int size = f1.getSize();
        int[] coordinates = new int[2];
        coordinates[0] = -1;
        int[][] field = transferFieldIntoIntegers(f1);
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if((field[i][j] == 3)&&(((f1.isValidCoord(i+1, j, size)&&(field[i+1][j] == 1)))
                        ||((f1.isValidCoord(i-1, j, size)&&(field[i-1][j] == 1)))
                        ||((f1.isValidCoord(i, j+1, size)&&(field[i][j+1] == 1)))
                        ||((f1.isValidCoord(i, j-1, size)&&(field[i][j-1] == 1)))
                        ||((f1.isValidCoord(i+1, j+1, size)&&(field[i+1][j+1] == 1)))
                        ||((f1.isValidCoord(i+1, j-1, size)&&(field[i+1][j-1] == 1)))
                        ||((f1.isValidCoord(i-1, j+1, size)&&(field[i-1][j+1] == 1)))
                        ||((f1.isValidCoord(i-1, j-1, size)&&(field[i-1][j-1] == 1)))))
                {
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        return coordinates;
    }

    private static boolean victoryCheck(Field f1, int size)
    {
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (f1.getCell(x, y) == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static void writeWhoIsWinner(Field f1, Field f2)
    {
        int SIZE = f1.getSize();
        if (victoryCheck(f1, SIZE) && victoryCheck(f2, SIZE))
        {
            writeln("Row!");
        }
        else
        {
            if (victoryCheck(f2, SIZE))
            {
                writeln("Looser!");
            }
            if (victoryCheck(f1, SIZE))
            {
                writeln("Winner!");
            }
        }
    }
}