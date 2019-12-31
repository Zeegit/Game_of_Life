package life;

public class Field {
    private int[][] life;
    private int size;
    private int step;

    public Field(int size) {
        this.size = size;
        life = new int[this.size][this.size];
    }

    int calcNeighbors(int x, int y) {
        int count = 0;
        int x_m1 = (size + x - 1) % size;
        int y_m1 = (size + y - 1) % size;
        int x_p1 = (x + 1) % size;
        int y_p1 = (y + 1) % size;
        if (life[x_m1][y_m1] == 1) { count++; }
        if (life[x_m1][y   ] == 1) { count++; }
        if (life[x_m1][y_p1] == 1) { count++; }
        if (life[x   ][y_m1] == 1) { count++; }
        if (life[x   ][y_p1] == 1) { count++; }
        if (life[x_p1][y_m1] == 1) { count++; }
        if (life[x_p1][y   ] == 1) { count++; }
        if (life[x_p1][y_p1] == 1) { count++; }
        return count;
    }

    void set(int x, int y, int value) {
        life[x][y] = value;
    }

    public int getSize() {
        return size;
    }

    public int getAlive() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (life[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int get(int x, int y) {
        return life[x][y];
    }

    public void assign(int[][] next) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                life[i][j] = next[i][j];
            }
        }
        step++;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
