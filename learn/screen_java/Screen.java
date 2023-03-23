import javax.swing.*;
import java.awt.*;


public class Screen extends JFrame {
    
    private JButton myButton;
    private JTextField myTextField;

    public Screen() {
        super("Ma fenêtre");

        myButton = new JButton("Cliquez ici");
        myTextField = new JTextField(20);

        setLayout(new BorderLayout());
        add(myButton, BorderLayout.NORTH);
        add(myTextField, BorderLayout.CENTER);

        setSize(1000, 1000);

        JLabel label = new JLabel(new ImageIcon("./test.png")); 
        label.setBounds(50, 50, 200, 100); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        myButton.addActionListener(e -> {
            String text = myTextField.getText();
            JOptionPane.showMessageDialog(this, "Vous avez entré : " + text);
        });
    }

    public static void main(String[] args) {
        Screen myScreen = new Screen();
    }
}
