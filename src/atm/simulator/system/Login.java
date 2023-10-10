package atm.simulator.system;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

  // Created the constructor as it will run by default on the creation of object when the class is run
  Login() {
    setTitle("AUTOMATED TELLER MACHINE");

    setLayout(null);

    // For using the image from the location specified we need to create an object and then use that object
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
    Image iLogo = logo.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    ImageIcon i2Logo = new ImageIcon(iLogo);

    //  We directly can not place image object on jframe so jlabel object is created
    JLabel logoLabel = new JLabel(i2Logo);
    logoLabel.setBounds(70, 10, 100, 100);
    //  for adding the image add method is used and inside jlabel object is passed
    add(logoLabel);

    //  Size of JFrame length, breadth
    setSize(800, 480);
    //  Made visible true as by default visibilty is hidden
    setVisible(true);
    // Made open at our specified location x,y
    setLocation(350, 200);
  }

  public static void main(String[] args) {
    //  Created an anonymous object
    new Login();
  }
}
