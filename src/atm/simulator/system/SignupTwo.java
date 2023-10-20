package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {


    JTextField panNoTextField, aadharNoTextField;
    JButton next;
    JRadioButton seniorYes, seniorNo, accountYes, accountNo;
    JComboBox<String[]> religionDropDown,categoryDropDown,incomeDropDown,educationDropDown,occupationDropDown;
    String formNo;

    SignupTwo(String formNo) {

        this.formNo = formNo;

        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");


        JLabel additionalDetails=new JLabel("Page 2 : Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);

        JLabel religion=new JLabel("Religion : " );
        religion.setFont(new Font("Raleway", Font.BOLD,20));
        religion.setBounds(100,140,100,30);
        add(religion);

        String[] religionArr = {"Sanatani", "Sikh", "Muslim", "Christian", "Others"};
        religionDropDown = new JComboBox(religionArr);
        religionDropDown.setBounds(300,140,400,30);
        religionDropDown.setBackground(Color.WHITE);
        add(religionDropDown);


        JLabel category=new JLabel("Category : " );
        category.setFont(new Font("Raleway", Font.BOLD,20));
        category.setBounds(100,190,200,30);
        add(category);

        String[] categoryArr = {"General", "OBC", "SC", "ST", "Others"};
        categoryDropDown = new JComboBox(categoryArr);
        categoryDropDown.setBounds(300,190,400,30);
        categoryDropDown.setBackground(Color.WHITE);
        add(categoryDropDown);


        JLabel income=new JLabel("Income : " );
        income.setFont(new Font("Raleway", Font.BOLD,20));
        income.setBounds(100,240,200,30);
        add(income);

        String[] incomeArr = {"Zero", "< 1,50,000", "< 3,00,000", "< 5,00,000", ">= 5,00,000"};
        incomeDropDown = new JComboBox(incomeArr);
        incomeDropDown.setBounds(300,240,400,30);
        incomeDropDown.setBackground(Color.WHITE);
        add(incomeDropDown);


        JLabel educational=new JLabel("Educational" );
        educational.setFont(new Font("Raleway", Font.BOLD,20));
        educational.setBounds(100,290,200,30);
        add(educational);


        JLabel qualification=new JLabel("Qualification : " );
        qualification.setFont(new Font("Raleway", Font.BOLD,20));
        qualification.setBounds(100,320,200,30);
        add(qualification);

        String[] educationArr = {"Non-Graduate", "Graduated", "Post-Graduated", "Doctorate", "Others"};
        educationDropDown = new JComboBox(educationArr);
        educationDropDown.setBounds(300,305,400,30);
        educationDropDown.setBackground(Color.WHITE);
        add(educationDropDown);


        JLabel occupation=new JLabel("Occupation : " );
        occupation.setFont(new Font("Raleway", Font.BOLD,20));
        occupation.setBounds(100,390,200,30);
        add(occupation);

        String[] occupationArr = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Others"};
        occupationDropDown = new JComboBox(occupationArr);
        occupationDropDown.setBounds(300,390,400,30);
        occupationDropDown.setBackground(Color.WHITE);
        add(occupationDropDown);


        JLabel panNo=new JLabel("PAN Number : " );
        panNo.setFont(new Font("Raleway", Font.BOLD,20));
        panNo.setBounds(100,440,200,30);
        add(panNo);

        panNoTextField=new JTextField();
        panNoTextField.setFont(new Font("Raleway",Font.BOLD,14));
        panNoTextField.setBounds(300,440,400,30);
        add(panNoTextField);

        JLabel aadharNo=new JLabel("Aadhar Number : " );
        aadharNo.setFont(new Font("Raleway", Font.BOLD,20));
        aadharNo.setBounds(100,490,200,30);
        add(aadharNo);

        aadharNoTextField=new JTextField();
        aadharNoTextField.setFont(new Font("Raleway",Font.BOLD,14));
        aadharNoTextField.setBounds(300,490,400,30);
        add(aadharNoTextField);

        JLabel senior=new JLabel("Senior Citizen : " );
        senior.setFont(new Font("Raleway", Font.BOLD,20));
        senior.setBounds(100,540,200,30);
        add(senior);



        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(300, 540, 60, 30);
        seniorYes.setBackground(Color.WHITE);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(450, 540, 60, 30);
        seniorNo.setBackground(Color.WHITE);
        add(seniorNo);

        ButtonGroup seniorCitizen = new ButtonGroup();
        seniorCitizen.add(seniorYes);
        seniorCitizen.add(seniorNo);

        JLabel account=new JLabel("Existing Account : " );
        account.setFont(new Font("Raleway", Font.BOLD,20));
        account.setBounds(100,590,200,30);
        add(account);

        accountYes = new JRadioButton("Yes");
        accountYes.setBounds(300, 590, 60, 30);
        accountYes.setBackground(Color.WHITE);
        add(accountYes);

        accountNo = new JRadioButton("No");
        accountNo.setBounds(450, 590, 60, 30);
        accountNo.setBackground(Color.WHITE);
        add(accountNo);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(accountYes);
        accountGroup.add(accountNo);

        next=new JButton("Next");
        next.setBorderPainted(false);
        next.setOpaque(true);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);


        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String religion = (String)religionDropDown.getSelectedItem();
        String category = (String)categoryDropDown.getSelectedItem();
        String income = (String)incomeDropDown.getSelectedItem();
        String education = (String)educationDropDown.getSelectedItem();
        String occupation = (String)occupationDropDown.getSelectedItem();
        String panNo = panNoTextField.getText();
        String aadharNo = aadharNoTextField.getText();
        String seniorCitizen = null;
        String existingAccount = null;
        if(seniorYes.isSelected()) {
            seniorCitizen = "Yes";
        } else if (seniorNo.isSelected()) {
            seniorCitizen = "No";
        }

        if (accountYes.isSelected()) {
            existingAccount = "Yes";
        } else if (accountNo.isSelected()) {
            existingAccount = "No";
        }

        try {
            Conn c = new Conn();
            String query = "INSERT INTO SignupTwo VALUES('"+formNo+"','"+religion+"','"+category+"','"+income+"','"+education+"','"+occupation+"','"+panNo+"','"+aadharNo+"','"+seniorCitizen+"','"+existingAccount+"')";
            c.s.executeUpdate(query);

            setVisible(false);
            // new class object will be defined here

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public static void main(String[] args) {
        new SignupTwo("");
    }
}
