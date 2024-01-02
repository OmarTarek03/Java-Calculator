package calculatorPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {
    private DecimalFormat df = new DecimalFormat("#,###.00");

    private String[] symbols = {

            "EXIT", "π", "e", "C", "\u232B",
            "x\u00B2","1/x", "|x|", "exp" , "n!",
            "\u221Ax", "sin(x)", "cos(x)", "tan(x)", "÷",
            "x\u02B8", "7", "8", "9", "x",
            "10\u02E3", "4", "5", "6", "-",
            "log", "1", "2", "3", "+",
            "ln", "+/-", "0", ".", "="
    };

    private int operator = 0;
    private JPanel panel = new JPanel(new BorderLayout(5,5));
    private JPanel buttonPanel = new JPanel(new GridLayout(7,5,3,3));
    private JButton[] buttons = new JButton[35];
    private JTextArea screen = new JTextArea(5,40);
    private double firstNum = 0, secondNum = 0;
    private JTextField calculatingTf = new JTextField(40);

    public Calculator(){
        init();
    }

    private void init(){
        screen.setFont(new Font("Arial",Font.BOLD, 22));
        setTitle("Calculator");
        screen.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        buttonPanel.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);

        for (int i = 0; i < Math.min(buttons.length, symbols.length); i++) {
            buttons[i] = new JButton(symbols[i]);

            switch (String.valueOf(symbols[i])) {
                case "+":
                case "-":
                case "x":
                case "÷":
                case "=": {
                    buttons[i].setBackground(new Color(245, 138, 2));
                    buttons[i].setForeground(Color.WHITE);
                    buttons[i].setFont(new Font("Digital-7", Font.BOLD, 24));
                    break;
                }

                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":{
                    buttons[i].setBackground(new Color(137, 137, 138));
                    buttons[i].setForeground(Color.WHITE);
                    buttons[i].setFont(new Font("Digital-7", Font.BOLD, 24));
                    break;
                }

                case "\u232B":{
                    buttons[i].setBackground(new Color(232, 231, 231));
                    buttons[i].setForeground(Color.BLACK);
                    buttons[i].setFont(new Font("Digital-7", Font.PLAIN, 20));
                }

                default: {
                    buttons[i].setBackground(new Color(87, 86, 86));
                    buttons[i].setForeground(Color.WHITE);
                    buttons[i].setFont(new Font("Digital-7", Font.BOLD, 20));
                }
            }

            buttons[i].addActionListener(this);


            buttonPanel.add(buttons[i]);
        }


        calculatingTf.setForeground(Color.WHITE);
        calculatingTf.setBackground(Color.BLACK);

        panel.add(calculatingTf, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(screen, BorderLayout.NORTH);
        add(panel);
        setSize(720,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private double factorial(double n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd) {
            case ".":{
                if (!screen.getText().contains(".")) {
                    screen.setText((screen.getText()) + ".");
                }
                break;
            }

            case "0": {
                screen.setText(screen.getText() + "0");
                break;
            }
            case "1": {
                screen.setText(screen.getText() + "1");
                break;
            }
            case "2": {
                screen.setText(screen.getText() + "2");
                break;
            }
            case "3": {
                screen.setText(screen.getText() + "3");
                break;
            }
            case "4": {
                screen.setText(screen.getText() + "4");
                break;
            }
            case "5": {
                screen.setText(screen.getText() + "5");
                break;
            }
            case "6": {
                screen.setText(screen.getText() + "6");
                break;
            }
            case "7": {
                screen.setText(screen.getText() + "7");
                break;
            }
            case "8": {
                screen.setText(screen.getText() + "8");
                break;
            }
            case "9": {
                screen.setText(screen.getText() + "9");
                break;
            }

            case "+":{
                if(!screen.getText().isEmpty()){
                    operator = 1;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }

            case "-":{
                if(!screen.getText().isEmpty()){
                    operator = 2;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }

            case "x":{
                if(!screen.getText().isEmpty()){
                    operator = 3;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }

            case "÷":{
                if(!screen.getText().isEmpty()){
                    operator = 4;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }


            case "+/-":{
                double num = Double.parseDouble(screen.getText());
                num *= -1;
                screen.setText(String.valueOf(num));
                break;
            }

            case "C":{
                screen.setText("");
                break;
            }

            case "\u232B": { //backspace
                String currentText = screen.getText();
                if (!currentText.isEmpty()) {
                    screen.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            }

            case "x\u00B2": { // x squared
                double num = Double.parseDouble(screen.getText());
                double square = num * num;
                screen.setText(String.valueOf(square));
                calculatingTf.setText(df.format(num) + " ^ 2 = " + square);
                break;
            }


            case "x\u02B8": { //x ^ y
                if(!screen.getText().isEmpty()){
                    operator = 5;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }

            case "exp":{
                if(!screen.getText().isEmpty()) {
                    operator = 6;
                    firstNum = Double.parseDouble(screen.getText());
                    screen.setText("");
                }
                break;
            }

            case "|x|":{
                double num = Double.parseDouble(screen.getText());
                double abs = Math.abs(num);
                screen.setText(String.valueOf(abs));
                calculatingTf.setText("| " + df.format(num) + " |  = " + df.format(abs));
                break;
            }

            case "1/x":{
                double num = Double.parseDouble(screen.getText());
                double ans = 1/(num);
                screen.setText(String.valueOf(ans));
                calculatingTf.setText("1 / " + df.format(num) + " = " + df.format(ans));
                break;
            }

            case "π": {
                String formattedPi = String.valueOf(Math.PI);
                screen.setText(formattedPi);
                calculatingTf.setText("π = " + formattedPi);
                break;
            }


            case "e":{
                screen.setText(String.valueOf(Math.E));
                calculatingTf.setText("e = " + Math.E);
            }

            case "sin(x)": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.sin(Math.toRadians(num)); // Assuming input is in degrees
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("sin(" + df.format(num) + ") = " + df.format(result));
                }
                break;
            }

            case "cos(x)": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.cos(Math.toRadians(num)); // Assuming input is in degrees
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("cos(" + df.format(num) + ") = " + df.format(result));
                }
                break;
            }

            case "tan(x)": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.tan(Math.toRadians(num)); // Assuming input is in degrees
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("tan(" + df.format(num) + ") = " + df.format(result));
                }
                break;
            }

            case "log": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.log10(num);
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("log(" + df.format(num) + ") = " + df.format(result));
                }
                break;
            }

            case "ln": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.log(num);
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("ln(" + df.format(num) + ") = " + df.format(result));
                }
                break;
            }

            case "10\u02E3": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.pow(10, num);
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("10^" + df.format(num) + " = " + df.format(result));
                }
                break;
            }

            case "√x": { // Square root
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    if (num >= 0) {
                        double result = Math.sqrt(num);
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText("√" + df.format(num) + " = " + df.format(result));
                    } else {
                        screen.setText("Error");
                        calculatingTf.setText("Invalid input for square root");
                    }
                }
                break;
            }

            case "mod": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    double result = Math.abs(num);
                    screen.setText(String.valueOf(result));
                    calculatingTf.setText("|" + df.format(num) + "| = " + df.format(result));
                }
                break;
            }

            case "n!": {
                if (!screen.getText().isEmpty()) {
                    double num = Double.parseDouble(screen.getText());
                    if (num >= 0 && num == (int) num) {
                        double result = factorial(num);
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText(df.format(num) + "! = " + df.format(result));
                    } else {
                        screen.setText("Error");
                        calculatingTf.setText("Invalid input for factorial");
                    }
                }
                break;
            }

            case "EXIT": {
                System.exit(0);
                break;
            }

        }

        if(cmd.equalsIgnoreCase("=")){

            if(!screen.getText().isEmpty()){

                secondNum = Double.parseDouble(screen.getText());

                switch (operator){
                    case 1: { // Addition
                        double result = firstNum + secondNum;
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText(df.format(firstNum) + " + " + df.format(secondNum) + " = " + df.format(result));
                        break;
                    }

                    case 2:{    // Subtraction
                        screen.setText(String.valueOf(firstNum - secondNum));
                        calculatingTf.setText(df.format(firstNum) + " - " + df.format(secondNum) + " = " + df.format(firstNum - secondNum));
                        break;
                    }

                    case 3: { // Multiplication
                        double result = firstNum * secondNum;
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText(df.format(firstNum) + " x " + df.format(secondNum) + " = " + df.format(result));
                        break;
                    }

                    case 4: { // Division
                        if (secondNum != 0) {
                            screen.setText(String.valueOf(firstNum / secondNum));
                            calculatingTf.setText(String.valueOf(df.format(firstNum) + " ÷ " + df.format(secondNum) + " = " + df.format(firstNum / secondNum)));
                        } else {
                            screen.setText("Error");
                            calculatingTf.setText("Cannot divide by zero");
                        }
                        break;
                    }

                    case 5: { // x ^ y
                        double result = Math.pow(firstNum, secondNum);
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText(df.format(firstNum) + " ^ " + df.format(secondNum) + " = " + df.format(result));
                        break;
                    }

                    case 6: { // x * 10 ^ y
                        double result = firstNum * Math.pow(10, secondNum);
                        screen.setText(String.valueOf(result));
                        calculatingTf.setText(df.format(firstNum) + " e" + df.format(secondNum) + " = " + df.format(result));
                        break;
                    }
                }

            }

        }
    }
}
