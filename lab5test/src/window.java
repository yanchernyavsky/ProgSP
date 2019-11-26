

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class window extends JFrame
{

    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("notes.txt"));
    ArrayList<person> persons = new ArrayList<>();
    boolean gender;

    JTextField textName = new JTextField("Name");
    JTextField textSurname = new JTextField("Surname");
    JTextField textPatronymic  = new JTextField("Patronymic ");
    JRadioButton maleButton = new JRadioButton("Male");
    JRadioButton femaleButton = new JRadioButton("Female");
    ButtonGroup genderButtons;
    MaskFormatter dateFormat = new MaskFormatter("##.##.####");
    JFormattedTextField ageField = new JFormattedTextField(dateFormat);
    JTextArea someBiography = new JTextArea("Biography");
    JButton addButton = new JButton("Add Person");
    JTextArea informationText = new JTextArea("");
    String[] initialsList = {};
    JComboBox<String> personList = new JComboBox<>(initialsList);
    JButton checkInfoButton = new JButton("Check Information");
    Color c = new Color(1,0,0, 0);


    public window() throws ParseException, IOException
    {
        super("Autobiography");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        textName.setSize(120,20);
        textName.setLocation(10,10);
        panel.add(textName);

        textSurname.setSize(120,20);
        textSurname.setLocation(10,40);
        panel.add(textSurname);

        textPatronymic.setSize(120,20);
        textPatronymic.setLocation(10,70);
        panel.add(textPatronymic);

        genderButtons = new ButtonGroup();
        genderButtons.add(maleButton);
        genderButtons.add(femaleButton);
        maleButton.setSize(55,25);
        maleButton.setLocation(10,90);
        femaleButton.setSize(70,25);
        femaleButton.setLocation(65,90);

        maleButton.addActionListener(new ButtonActionListener());
        femaleButton.addActionListener(new ButtonActionListener());


        panel.add(femaleButton);
        panel.add(maleButton);

        dateFormat.setPlaceholderCharacter('_');
        ageField.setSize(120,20);
        ageField.setLocation(10,130);
        panel.add(ageField);


        someBiography.setSize(120,120);
        someBiography.setLocation(10,150);
        panel.add(someBiography);


        addButton.setSize(120,20);
        addButton.setLocation(10, 280);
        addButton.addActionListener(new OurActionListener());
        panel.add(addButton);


        personList.setSize(150,20);
        personList.setLocation(200, 10);
        panel.add(personList);


        checkInfoButton.setSize(150,20);
        checkInfoButton.setLocation(200, 40);
        checkInfoButton.addActionListener(new infActionListener());
        panel.add(checkInfoButton);


        informationText.setSize(150,250);
        informationText.setLocation(200,70);
        panel.add(informationText);

        setContentPane(panel);
        setSize(400, 400);
    }
    public class OurActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //in File
            persons.add(new person(textName.getText(), textSurname.getText(), textPatronymic.getText(), ageField.getText(), someBiography.getText(), gender));
            personList.addItem(persons.get(persons.size()-1).getInitials());
            try
            {
                out.writeObject(persons.get(persons.size()-1));
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public class infActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            //from file
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("notes.txt")))
            {
                for (int i = 0; i < persons.size(); i++)
                {
                    person temp = (person)objectInputStream.readObject();
                    if(personList.getSelectedItem().toString().equals(temp.getInitials()))
                    {
                        informationText.setText(temp.getInformation());
                        informationText.setEnabled(false);
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public class ButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            {
                if(e.getSource() == maleButton)
                    gender = true;
                if(e.getSource() == femaleButton)
                    gender = false;
            }
        }
    }

}