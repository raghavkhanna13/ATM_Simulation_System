package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    String cardNumber, pinNumber;
    JPasswordField newPinTextField, reEnterPinTextField;
    JButton changePin, back;

    PinChange(String cardNumber, String pinNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel newPin = new JLabel("New Pin: ");
        newPin.setForeground(Color.WHITE);
        newPin.setFont(new Font("Raleway", Font.BOLD, 16));
        newPin.setBounds(165, 320, 180, 25);
        image.add(newPin);

        newPinTextField = new JPasswordField();
        newPinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        newPinTextField.setBounds(330,320, 180, 25);
        image.add(newPinTextField);

        JLabel reEnterPin = new JLabel("Re-Enter New Pin: ");
        reEnterPin.setForeground(Color.WHITE);
        reEnterPin.setFont(new Font("Raleway", Font.BOLD, 16));
        reEnterPin.setBounds(165, 360, 180, 25);
        image.add(reEnterPin);

        reEnterPinTextField = new JPasswordField();
        reEnterPinTextField.setFont(new Font("Raleway", Font.BOLD, 25));
        reEnterPinTextField.setBounds(330,360, 180, 25);
        image.add(reEnterPinTextField);

        changePin = new JButton("Change");
        changePin.setBounds(355, 485, 150, 30);
        changePin.addActionListener(this);
        image.add(changePin);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changePin) {
            try {
                String newPin = newPinTextField.getText();
                String reEnteredPin = reEnterPinTextField.getText();

                if(!newPin.equals(reEnteredPin)) {
                    JOptionPane.showMessageDialog(null, "Your Pin does not match");
                    return;
                }

                if(newPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter new Pin");
                    return;
                }

                if(reEnteredPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-Enter new Pin");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "Update amountDetails set Pin = '"+newPin+"' where Pin =  '"+pinNumber+"' AND card_number = '"+cardNumber+"'";
                String query2 = "Update signupthree set Pin = '"+newPin+"' where Pin =  '"+pinNumber+"' AND card_number = '"+cardNumber+"'";
                String query3 = "Update login set Pin = '"+newPin+"' where Pin =  '"+pinNumber+"' AND card_number = '"+cardNumber+"'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "Pin changed successfully!");

                setVisible(false);
                new Login().setVisible(true);

            } catch (Exception exception) {
                System.out.println(exception);
            }
        } else {
            setVisible(false);
            new Transactions(cardNumber, pinNumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("", "");
    }
}
