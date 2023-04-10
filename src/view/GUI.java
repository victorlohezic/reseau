import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    int nbFishes;
    ImageIcon[] icons;
    JLabel[] labels;

    public GUI(View newView) {
        super("View");
        setLayout(null); // creates the window


        //ImageIcon icon = new ImageIcon("clown.jpeg"); // creates a picture
        //myLabel = new JLabel(icon); //converts to a label to display
        //myLabel.setBounds(x_fish, y_fish, 219, 171); //location, size of the picture
        

        // Create the timer to update the button position
        //timer = new Timer(100, this);
        //timer.start();



        setLocation(newView.getPosition()[0], newView.getPosition()[1]); //sets the location of the window on the screen at
        setSize(newView.getDimensions()[0], newView.getDimensions()[1]); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window
        getContentPane().setBackground(Color.BLUE); // set the bg to blue

    }



    public void updateView(View newView) {

        for (int i=0; i<this.nbFishes; i++) {
            this.remove(labels[i]);
        }

        this.nbFishes = newView.getFishes().size();
        this.icons = new ImageIcon[nbFishes];
        this.labels = new JLabel[nbFishes];

        // Iterate over the keys using a for-each loop
        int count = 0;
        for (String name : newView.getFishes().keySet()) {

            Fish fish = newView.getFishes().get(name);
            icons[count] = new ImageIcon("clown.jpeg");
            labels[count] = new JLabel(icons[count]);
            labels[count].setBounds(fish.getPosition()[0],
                                    fish.getPosition()[1],
                                    fish.getSize()[0],
                                    fish.getSize()[1]);

            this.add(labels[count]);
        }

    }


/*
    public void actionPerformed(ActionEvent e) {
        add(myLabel); // adds the picture to the window

        if (e.getSource() == timer) {
            
            // Iterate over the keys using a for-each loop
            for (String key : map.keySet()) {
                System.out.println(key);
            }

            // Set the label position
            myLabel.setLocation(x_fish, y_fish);
        }
    
    }
*/


    public static void main(String[] args) {
        //GUI myGui = new GUI(100, 100, 1000, 1000);
    }
}