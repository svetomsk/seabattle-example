package battlesea;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean victory = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Field f1 = new Field();
        Field f2 = new Field();
        f1.fillFieldByRandom();
        fillFieldByPlayer(f2, br);
        writeln("");
        final int SIZE = f1.getSize();
        Random random = new Random();
        printInvisibleFields(f1);
        while (!victory) {
            makePlayerTurn(f1, br);
            makeComputerTurn(f2, random);
            printVisibleField(f1);
            printVisibleField(f2);
            victory = (victoryCheck(f1, SIZE) || victoryCheck(f2, SIZE));
        }
        writeWhoIsWinner(f1, f2);
    }

    private static void writeln(String s) {
        System.out.println(s);
    }

    private static void write(String s) {
        System.out.print(s);
    }

    private static int[][] transferFieldIntoIntegers(Field f1) {
        int size = f1.getSize();
        int[][] Field = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Field[x][y] = f1.getCell(x, y);
            }
        }
        return Field;
    }

    private static void fillFieldByPlayer(Field f2, BufferedReader br) {
        int s = f2.getSmallShips();
        int m = f2.getMediumShips();
        int size = f2.getSize();
        fillFieldWithSmallShipsByPlayer(f2, br);
//        fillFieldWithMediumShipsByPlayer(f2, br);
//        fillFieldWithBigShipsByPlayer(f2, br);
//        fillFieldWithHugeShipsByPlayer(f2, br);
    }

    private static void fillFieldWithSmallShipsByPlayer(Field f2, BufferedReader br) {
        int s = f2.getSmallShips();
        int size = f2.getSize();
        for (int k = 0; k < s;) {
            try {
                writeln("Write the x, y coordinates of the cell you want"
                        + " to place a small ship into");
                int x = Integer.valueOf(br.readLine());
                int y = Integer.valueOf(br.readLine());
                int[][] Field = transferFieldIntoIntegers(f2);
                if (f2.areNeighborsFree(x, y, Field)) {
                    f2.setCell(x, y, 1);
                    k++;
                } else {
                    writeln("The cell is placed incorrectly");
                }
            } catch (IOException e) {
                writeln("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }

        }
    }

    private static void fillFieldWithMediumShipsByPlayer(Field f2, BufferedReader br) {
        int m = f2.getMediumShips();
        int size = f2.getSize();
        for (int k = 0; k < m;) {
            try {
                writeln("Write the x1, y1, x2, y2 coordinates of the cells you want"
                        + " to place a medium ship into");
                int x1 = Integer.valueOf(br.readLine());
                int y1 = Integer.valueOf(br.readLine());
                int x2 = Integer.valueOf(br.readLine());
                int y2 = Integer.valueOf(br.readLine());
                int[][] Field = transferFieldIntoIntegers(f2);
                if (f2.areNeighborsFree(x1, y1, Field)
                        && f2.areNeighborsFree(x2, y2, Field)
                        && ((Math.abs(x1 - x2) == 1) || Math.abs(y1 - y2) == 1)
                        && !((Math.abs(x1 - x2) == 1) && Math.abs(y1 - y2) == 1)
                        && f2.isValidCoord(x2, y2)) {
                    f2.setCell(x1, y1, 1);
                    f2.setCell(x2, y2, 1);
                    k++;
                } else {
                    writeln("The cell is placed incorrectly");
                }
            } catch (IOException e) {
                writeln("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }
        }
    }

    
    private static void fillFieldWithBigShipsByPlayer(Field f2, BufferedReader br) {
        int b = f2.getBigShips();
        int size = f2.getSize();
        for (int k = 0; k < b;) {
            try {
                writeln(String.format("Write the x1, y1, x2, y2 coordinates of the cells you want"
                        + " to place a %s ship into", "big"));
                int x1 = Integer.valueOf(br.readLine());
                int y1 = Integer.valueOf(br.readLine());
                int x2 = Integer.valueOf(br.readLine());
                int y2 = Integer.valueOf(br.readLine());
                int[][] Field = transferFieldIntoIntegers(f2);
                if (f2.areNeighborsFree(x1, y1, Field)
                        && f2.areNeighborsFree(x2, y2, Field)
                        && ((Math.abs(x1 - x2) == 2) || Math.abs(y1 - y2) == 2)
                        && !((Math.abs(x1 - x2) == 2) && Math.abs(y1 - y2) == 2)
                        && f2.isValidCoord(x2, y2)) {
                    f2.setCell(x1, y1, 1);
                    f2.setCell((x1+x2)/2,(y1+y2)/2, 1);
                    f2.setCell(x2, y2, 1);
                    k++;
                } else {
                    writeln("The cell is placed incorrectly");
                }
            } catch (IOException e) {
                writeln("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }
        }
    }
    
    
    private static void fillFieldWithHugeShipsByPlayer(Field f2, BufferedReader br) {
        int h = f2.getHugeShips();
        int size = f2.getSize();
        for (int k = 0; k < h;) {
            try {
                writeln("Write the x1, y1, x2, y2 coordinates of the cells you want"
                        + " to place a huge ship into");
                int x1 = Integer.valueOf(br.readLine());
                int y1 = Integer.valueOf(br.readLine());
                int x2 = Integer.valueOf(br.readLine());
                int y2 = Integer.valueOf(br.readLine());
                int[][] Field = transferFieldIntoIntegers(f2);
                if (f2.areNeighborsFree(x1, y1, Field)
                        && f2.areNeighborsFree(x2, y2, Field)
                        && ((Math.abs(x1 - x2) == 3) || Math.abs(y1 - y2) == 3)
                        && !((Math.abs(x1 - x2) == 3) && Math.abs(y1 - y2) == 3)
                        && f2.isValidCoord(x2, y2)) {
                    f2.setCell(x1, y1, 1);
                    f2.setCell((x1+x2+1)/2,(y1+y2+1)/2, 1);
                    f2.setCell((x1+x2-1)/2,(y1+y2-1)/2, 1);
                    f2.setCell(x2, y2, 1);
                    k++;
                } else {
                    writeln("The cell is placed incorrectly");
                }
            } catch (IOException e) {
                writeln("please write a number from 0 to "
                        + Integer.toString(size) + ", not anything else");
            }
        }
    }
    
    private static void makePlayerTurn(Field f1, BufferedReader br) {
        boolean turn = false;
        int size = f1.getSize();
        try {
            writeln("Write the x, y coordinate of the cell you want"
                    + " to destroy");
            while (!turn) {
                int x = Integer.valueOf(br.readLine());
                int y = Integer.valueOf(br.readLine());
                if (f1.getCell(x, y) == 1) {
                    f1.setCell(x, y, 3);
                    turn = true;
                }
                if (f1.getCell(x, y) == 0) {
                    f1.setCell(x, y, 2);
                    turn = true;
                }
                if (!turn) {
                    writeln("Choose undestroyed cell");
                }
            }
        } catch (IOException e) {
            writeln("Please write a number from 0 to "
                    + Integer.toString(size) + ", not anything else");
        }
    }

    private static void makeComputerTurn(Field f2, Random random) {
        int size = f2.getSize();
        boolean turn = false;
        while (!turn) {
            int r1 = Math.abs(random.nextInt()) % (size);
            int r2 = Math.abs(random.nextInt()) % (size);
            if (f2.isValidCoord(r1, r2, size)) {
                if (f2.getCell(r1, r2) == 1) {
                    f2.setCell(r1, r2, 3);
                    turn = true;
                }
                if (f2.getCell(r1, r2) == 0) {
                    f2.setCell(r1, r2, 2);
                    turn = true;
                }
            }
        }
    }

    private static void printVisibleField(Field f1) {
        int size = f1.getSize();
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                if ((f1.getCell(k, l) == 1) || (f1.getCell(k, l) == 0)) {
                    write("~ ");
                }
                if (f1.getCell(k, l) == 3) {
                    write("X ");
                }
                if (f1.getCell(k, l) == 2) {
                    write("0 ");
                }
            }
            writeln(" ");
        }
        writeln(" ");
    }

    private static void printInvisibleFields(Field f1) {
        int size = f1.getSize();
        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                if (f1.getCell(k, l) == 1) {
                    write("S ");
                }
                if (f1.getCell(k, l) == 0) {
                    write("~ ");
                }
                if (f1.getCell(k, l) == 3) {
                    write("X ");
                }
                if (f1.getCell(k, l) == 2) {
                    write("0 ");
                }
            }
            writeln(" ");
        }
        writeln(" ");
    }

    private static boolean victoryCheck(Field f1, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (f1.getCell(x, y) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void writeWhoIsWinner(Field f1, Field f2) {
        int SIZE = f1.getSize();
        if (victoryCheck(f1, SIZE) && victoryCheck(f2, SIZE)) {
            writeln("Row!");
        } else {
            if (victoryCheck(f2, SIZE)) {
                writeln("Looser!");
            }
            if (victoryCheck(f1, SIZE)) {
                writeln("Winner!");
            }
        }
    }
}