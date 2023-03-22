import javax.swing.*;
import java.awt.*;

public class MyScreen extends JFrame {

    private JButton myButton;

    public MyScreen() {
        //super("My window");

        setLayout(null); // creates the window
  
        myButton = new JButton("My button");
        myButton.setBounds(400, 400, 100, 100); // coordinates , location
        add(myButton);

        ImageIcon icon = new ImageIcon("test.png");
        JLabel label = new JLabel(icon);
        label.setBounds(10, 10, 262, 111); 

        add(label);

        setLocation(800, 200); //sets the location of the window on the screen at
        setSize(500, 537); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window
    }


    public static void main(String[] args) {
        MyScreen myScreen = new MyScreen();
    }
}
