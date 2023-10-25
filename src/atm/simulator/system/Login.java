package atm.simulator.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    
    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    
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

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        // set the size and coordinates on the framne x,y,length,breadth
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        // set the size and coordinates on the framne x,y,length,breadth
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 15));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        // set the size and coordinates on the framne x,y,length,breadth
        pin.setBounds(120, 220, 150, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 15));
        add(pinTextField);

        // Buttons
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        //these two lines have been added to set the button display properly on mac too
        login.setOpaque(true);
        login.setBorderPainted(false);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        //these two lines have been added to set the button display properly on mac too
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        //these two lines have been added to set the button display properly on mac too
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        add(signup);


        getContentPane().setBackground(Color.WHITE);
        //  Size of JFrame length, breadth
        setSize(800, 480);
        //  Made visible true as by default visibilty is hidden
        setVisible(true);
        // Made open at our specified location x,y
        setLocation(350, 200);
    }
  
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(e.getSource() == login) {
            Conn conn = new Conn();
            String cardNumber = cardTextField.getText();
            String pinNumber = pinTextField.getText();
            String query = "Select * from login where card_number = '" +cardNumber + "' AND pin = '" + pinNumber + "'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()) {
                    setVisible(false);
                    new Transactions(pinNumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number Or Pin");
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
        else if(e.getSource() == signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    } 

    public static void main(String[] args) {
        //  Created an anonymous object
        new Login();
    }
}
