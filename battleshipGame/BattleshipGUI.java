import javax.swing.*;
import java.awt.*;

public class BattleshipGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(10, 10));

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JButton cell = new JButton();
                cell.setPreferredSize(new Dimension(40, 40));

                int r = row, c = col; // same reason as before — needed for the lambda below
                cell.addActionListener(e -> {
                    System.out.println("Clicked: " + r + ", " + c);
                    cell.setBackground(Color.RED);
                    cell.setOpaque(true);
                });

                grid.add(cell);
            }
        }

        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
    }
}