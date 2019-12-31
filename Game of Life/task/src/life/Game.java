package life;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.lang.Thread;

public class Game  {
    Field field;
    GameOfLife d;

    public void generate(int x) {
        Random rand = new Random();

        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                if (rand.nextBoolean()) {
                    field.set(i, j, 1);
                } else {
                    field.set(i, j, 0);
                }
            }
        }
        field.setStep(1);

    }

    public Game (int size, int x) {
        field = new Field(size);
        generate(x);
    }

    public Game (int size, int x, int step) {
        field = new Field(size);

        generate(x);
    }

    void doSomeSteps(int stepCount) {
        drawAndClear(1);
        for (int z = 0; z < stepCount; z++) {
            field.assign(nextStep());
            drawJFrame(z+2);
        }
   }

    private void drawJFrame(int i) {
    }

    void drawAndClear(int step)  {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {}

        System.out.println("Generation #"+step);
        System.out.println("Alive: "+field.getAlive());
        draw();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

    }



    private int[][] nextStep() {
        int[][] n = new int[field.getSize()][field.getSize()];
        int neighbors;
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                neighbors =  field.calcNeighbors(i, j);
                if (neighbors == 2) {
                    n[i][j] = field.get(i, j);
                } else if (neighbors == 3) {
                    n[i][j] = 1;
                } else {
                    n[i][j] = 0;
                }
            }
        }
        return n;
    }


    void draw() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                if (field.get(i, j) == 1) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void run() {
        d = new GameOfLife();
        d.reDraw(field, 0);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        while (true) {
            if (d.isActive) {
                field.assign(nextStep());
                d.reDraw(field, 0);
            }

            if (d.isRefresh) {
                d.isRefresh = false;
                d.isActive = false;
                generate(0);
                d.reDraw(field, 0);
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
        //drawAndClear(1);
    }
}
