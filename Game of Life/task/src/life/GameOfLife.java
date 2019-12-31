package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class JPanel2 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        g.drawRect(5,5,getWidth()-10,getHeight()-10);
    }
}

public class GameOfLife extends JFrame {
    JLabel aliveLabel;
    JLabel genLabel;

    Field field;
    JPanel2 centerPanel;
    JPanel topPanel;

    final int SIZE = 20;
    boolean isActive = false;
    boolean isRefresh = false;
    JToggleButton btnStart;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        centerPanel = new JPanel2();
        topPanel = new JPanel();

        genLabel = new JLabel("Generation #0");
        genLabel.setName("GenerationLabel");
        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));


        btnStart = new JToggleButton("Play");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isActive = !isActive;
                btnStart.setText(isActive? "Stop" : "Play");
            }
        });
        btnStart.setName("PlayToggleButton");
        topPanel.add(btnStart);

        JButton btnRefresh = new JButton("Refresh");

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isRefresh = true;

            }
        });
        btnRefresh.setName("ResetButton");
        topPanel.add(btnRefresh);



        topPanel.add(genLabel);
        topPanel.add(aliveLabel);


        //topPanel.setLayout (new BoxLayout (getContentPane(), BoxLayout.Y_AXIS));


        getContentPane().add(BorderLayout.CENTER, centerPanel);
        getContentPane().add(BorderLayout.WEST, topPanel);
        setVisible(true);




    }

    public void reDraw(Field field, int step) {
        genLabel.setText("Generation #"+field.getStep());
        aliveLabel.setText("Alive: "+field.getAlive());
        this.field = field;
        centerPanel.repaint();
    }

    public class JPanel2 extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < field.getSize(); x++) {
                for (int y = 0; y < field.getSize(); y++) {
                    if (field.get(x, y) == 1) {
                        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);

                    } else {
                        g.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
                    }
                }
            }
        }
    }

}
