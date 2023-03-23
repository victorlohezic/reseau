import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyGUI extends JFrame implements ActionListener {

    private JButton button;
    private Timer timer;

    private int x = 50; // starting x coordinate
    private int y = 50; // starting y coordinate
    private int dx = 2; // x movement speed
    private int dy = 1; // y movement speed

    public MyGUI() {
        super("Moving Button");
        setLayout(null);
        // Create the button
        button = new JButton("DVD");
        //button.setBounds(x , y, 50, 50); // Définir les coordonnées (50, 50) et la taille (100, 30) du bouton
        button.setSize(100,100);
        button.addActionListener(this);

        // Add the button to the JFrame
        add(button);

        // Create the timer to update the button position
        timer = new Timer(1, this);
        timer.start();

        // Set the JFrame properties
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            // Handle the button click event here
        } else if (e.getSource() == timer) {
            // Update the button position
            x += dx;
            y += dy;

            // Reverse direction if the button reaches the edge of the JFrame
            if (x <= 0 || x + button.getWidth() >= getContentPane().getWidth()) {
                dx = -dx;
            }
            if (y <= 0 || y + button.getHeight() >= getContentPane().getHeight()) {
                dy = -dy;
            }

            // Set the button position
            button.setLocation(x, y);
        }
    }

    public static void main(String[] args) {
        MyGUI myGUI = new MyGUI();
    }
}
