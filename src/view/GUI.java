import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;


public class GUI extends JFrame {//implements ActionListener{

    private int nbFishes=0;
    private ImageIcon[] icons;
    private JLabel[] labels;
    private JLabel[] myLabel;
    private int dx = 0;
    private int screenWidth;
    private int screenHeight;
    //private Timer timer;

    public GUI(View newView) {
        super("View");
        setLayout(null); // creates the window


        //Create the timer to update the button position
        //timer = new Timer(1000, this);
        //timer.start();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        System.out.print("dimensions ecran : "+ screenWidth + " "+ screenHeight);

        setLocation(newView.getPosition()[0]*screenWidth/100, newView.getPosition()[1]*screenHeight/100); //sets the location of the window on the screen at
        setSize(newView.getDimensions()[0]*screenWidth/100, newView.getDimensions()[1]*screenHeight/100); // sets the size of the window at 500x537 (500+height of stripe)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when exit
        setResizable(false); // prevents from resizing the window
        setVisible(true); // displays the window
        getContentPane().setBackground(Color.BLUE); // set the bg to blue

    }




    public void updateGUI(View newView) {

        System.out.print("update GUIIIIIIIII\n");

        
        for (int i=0; i<this.nbFishes; i++) {
            this.remove(labels[i]);
        }

        dx+=10;
        this.nbFishes = newView.getFishes().size();
        this.icons = new ImageIcon[nbFishes];
        this.labels = new JLabel[nbFishes];

        // Iterate over the keys using a for-each loop
        int count = 0;
        for (String name : newView.getFishes().keySet()) {
            Fish fish = newView.getFishes().get(name);
            icons[count] = new ImageIcon("clown.png");
            labels[count] = new JLabel(icons[count]);
            labels[count].setBounds(0, 0, fish.getSize()[0]*screenWidth/100, fish.getSize()[1]*screenHeight/100);
            this.add(labels[count]);
            labels[count].setLocation(fish.getPosition()[0]*screenWidth/100, fish.getPosition()[1]*screenHeight/100);
            count++;
        }
    }

    
/*
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            
            System.out.print("time a 0");
        }
    
    }

*/

    public static void main(String[] args) {
        //GUI myGui = new GUI();
    }
}
