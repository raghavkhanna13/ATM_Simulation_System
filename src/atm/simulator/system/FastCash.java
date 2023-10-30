package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton oneHundred, fiveHundred, oneThousand, twoThousand, fiveThousand, tenThousand, back;
    String pinNumber, cardNumber;

    FastCash(String cardNumber, String pinNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(215,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        oneHundred = new JButton("Rs 100");
        oneHundred.setBounds(170, 415, 150, 30);
        oneHundred.addActionListener(this);
        image.add(oneHundred);

        fiveHundred = new JButton("Rs 500");
        fiveHundred.setBounds(355, 415, 150, 30);
        fiveHundred.addActionListener(this);
        image.add(fiveHundred);

        oneThousand = new JButton("Rs 1000");
        oneThousand.setBounds(170, 450, 150, 30);
        oneThousand.addActionListener(this);
        image.add(oneThousand);

        twoThousand = new JButton("Rs 2000");
        twoThousand.setBounds(355, 450, 150, 30);
        twoThousand.addActionListener(this);
        image.add(twoThousand);

        fiveThousand = new JButton("Rs 5000");
        fiveThousand.setBounds(170, 485, 150, 30);
        fiveThousand.addActionListener(this);
        image.add(fiveThousand);

        tenThousand = new JButton("Rs 10000");
        tenThousand.setBounds(355, 485, 150, 30);
        tenThousand.addActionListener(this);
        image.add(tenThousand);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            setVisible(false);
            new Transactions(cardNumber, pinNumber).setVisible(true);
        } else {
            String money=((JButton)e.getSource()).getText().substring(3); //substring is used to start from numbers and leave the text that is Rs and 1 space.
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from amountDetails where pin ='"+pinNumber+"' AND card_number = '"+ cardNumber +"' ");
                int balance =0;
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance+=Integer.parseInt(rs.getString("amount"));
                    } else{
                        balance -=Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(e.getSource()!=back && balance < Integer.parseInt(money) ){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                Date date=new Date();
                String query="insert into amountDetails values('"+cardNumber+"','"+pinNumber+"','"+date+"','Withdrawal','"+money+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+money+" Debited Successfuly");

                setVisible(false);
                new Transactions(cardNumber, pinNumber).setVisible(true);
            } catch (Exception exception){
                System.out.println(exception);
            }
        }
    }
    public static void main(String[] args) {
        new FastCash("", "");
    }
}
