package view;

import javax.swing.*;

public class Interface {
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel principal;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton startButton;
    private JRadioButton timeStrategyRadioButton;
    private JRadioButton shortestQueueStrategyRadioButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;

    public Interface() {
        JFrame frame = new JFrame("Aranjare la coada");
        frame.setContentPane(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public void afisareTimp(String s)
    {
        textField1.setText(s);
    }
    public void afisareWTime(String s)
    {
        textField8.setText(s);
    }
    public void afisareSTime(String s)
    {
        textField9.setText(s);
    }
    public void afisareMaxTrafficTime(String s)
    {
        textField10.setText(s);
    }
    public void afisareCozi(String s)
    {
        textArea1.setText(s);
    }
    public int getTime()
    {
        return  Integer.parseInt(textField2.getText());
    }
    public int getMaxProcessingTime()
    {
        return Integer.parseInt(textField3.getText());
    }
    public int getMinProcessingTime()
    {
        return Integer.parseInt(textField4.getText());
    }
    public int getNumberOfServers()
    {
        return Integer.parseInt(textField5.getText());
    }
    public int getNumberOfClients()
    {
        return Integer.parseInt(textField6.getText());
    }
    public int getMaxArrivalTime()
    {
        return Integer.parseInt(textField7.getText());
    }
    public int getMinArrivalTime()
    {
        return Integer.parseInt(textField11.getText());
    }
    public JButton getButon(){
        return startButton;
    }
    public int selectedRadio1(){
        if(timeStrategyRadioButton.isSelected()){
            return 1;
        }
        else return 0;
    }
}
