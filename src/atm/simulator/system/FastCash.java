package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton deposit, withdrawal, fastCash, miniStatement, pinChange, balanceEnquiry, back;
    String pinNumber;

    FastCash(String pinNumber) {
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

        deposit = new JButton("Rs 100");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal = new JButton("Rs 500");
        withdrawal.setBounds(355, 415, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastCash = new JButton("Rs 1000");
        fastCash.setBounds(170, 450, 150, 30);
        fastCash.addActionListener(this);
        image.add(fastCash);

        miniStatement = new JButton("Rs 2000");
        miniStatement.setBounds(355, 450, 150, 30);
        deposit.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton("Rs 5000");
        pinChange.setBounds(170, 485, 150, 30);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balanceEnquiry = new JButton("Rs 10000");
        balanceEnquiry.setBounds(355, 485, 150, 30);
        balanceEnquiry.addActionListener(this);
        image.add(balanceEnquiry);

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
            new Transactions(pinNumber).setVisible(true);
        } else {
            String amount=((JButton)e.getSource()).getText().substring(3); //substring is used to start from numbers and leave the text that is Rs and 1 space.
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pin ='"+pinNumber+"' ");
                int balance =0;
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance+=Integer.parseInt(rs.getString("amount"));
                    } else{
                        balance -=Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(e.getSource()!=back && balance<Integer.parseInt(amount) ){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                Date date=new Date();
                String query="insert into bank values('"+pinNumber+"','"+date+"','Withdrawal','"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+amount+" Debited Successfuly");

                setVisible(false);
                new Transactions(pinNumber).setVisible(true);
            } catch (Exception ae){
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new FastCash("");
    }
}
