package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {

    String pinNumber, cardNumber;
    MiniStatement(String cardNumber, String pinNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;

        setLayout(null);

        setTitle("Mini Statement");

        JLabel bank = new JLabel("Indian bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);

        JLabel bal = new JLabel();
        bal.setBounds(20, 300, 300, 20);
        add(bal);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from login where card_number = '" +cardNumber + "' AND pin = '" + pinNumber + "'");
            while(rs.next()){
                card.setText("Card Number:    " + rs.getString("card_number").substring(0, 4) + "XXXXXXXX" + rs.getString("card_number").substring(12));
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        try {
            Conn c = new Conn();
            int balance = 0;
            ResultSet rs = c.s.executeQuery("select * from amountDetails where pin ='" + pinNumber + "' AND card_number = '" + cardNumber + "' ");

            while (rs.next()) {

                mini.setText(mini.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");

                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }

                bal.setText("Your total balance is " + balance);
            }
        } catch(Exception exception) {
            System.out.println(exception);
        }



        setSize( 400, 600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) {
        new MiniStatement("", "");
    }
}
