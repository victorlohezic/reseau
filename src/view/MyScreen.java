import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyScreen extends JFrame implements ActionListener {

    private JButton myButton;
    private JLabel myLabel;
    private Timer timer;

    private int x = 10; // starting x coordinate
    private int y = 10; // starting y coordinate
    private int dx = 2; // x movement speed
    private int dy = 1; // y movement speed    

    public MyScreen() {
        super("My window");

        setLayout(null); // creates the window

        myButton = new JButton("My button"); // creates a button
        //myButton.setBounds(400, 400, 100, 100); // location, size of the button


        //add(myButton); // adds the button to the window

        ImageIcon icon = new ImageIcon("clown.jpeg"); // creates a picture
        myLabel = new JLabel(icon); //converts to a label to display
        myLabel.setBounds(x, y, 219, 171); //location, size of the picture
        
        add(myLabel); // adds the picture to the window

        myButton.addActionListener(this);

        // Create the timer to update the button position
        timer = new Timer(100, this);
        timer.start();



        setLocation(800, 200); //sets the location of the window on the screen at
        setSize(1000, 1037); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window
        getContentPane().setBackground(Color.BLUE); // set the bg to blue

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            // Update the button position
            x += dx;
            y += dy;

            // Reverse direction if the button reaches the edge of the JFrame
            if (x <= 0 || x + myLabel.getWidth() >= getContentPane().getWidth()) {
                dx = -dx;
            }
            if (y <= 0 || y + myLabel.getHeight() >= getContentPane().getHeight()) {
                dy = -dy;
            }

            // Set the label position
            myLabel.setLocation(x, y);
        }
    }


    public static void main(String[] args) {
        MyScreen myScreen = new MyScreen();
    }
}
