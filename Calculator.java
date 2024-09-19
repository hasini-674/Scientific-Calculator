import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {
    private JTextField display;
    private double result = 0;
    private String lastCommand = "=";
    private boolean start = true;

    public Calculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 7, 2, 2));

        String[] buttonLabels = {
            "Rad", "Deg", "x!", "(", ")", "%", "AC",
            "Inv", "sin", "ln", "7", "8", "9", "÷",
            "π", "cos", "log", "4", "5", "6", "×",
            "e", "tan", "√", "1", "2", "3", "-",
            "Ans", "EXP", "x^y", "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if ("0123456789.".contains(command)) {
                if (start) {
                    display.setText(command);
                    start = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if ("+-×÷=".contains(command)) {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            } else if (command.equals("AC")) {
                display.setText("0");
                start = true;
                result = 0;
                lastCommand = "=";
            } else if (command.equals("√")) {
                double x = Double.parseDouble(display.getText());
                result = Math.sqrt(x);
                display.setText(String.valueOf(result));
                start = true;
            } else if (command.equals("sin") || command.equals("cos") || command.equals("tan")) {
                double x = Double.parseDouble(display.getText());
                switch (command) {
                    case "sin": result = Math.sin(Math.toRadians(x)); break;
                    case "cos": result = Math.cos(Math.toRadians(x)); break;
                    case "tan": result = Math.tan(Math.toRadians(x)); break;
                }
                display.setText(String.valueOf(result));
                start = true;
            }
            // Note: Other functions like ln, log, x!, x^y, etc. are not implemented in this basic version
        }
    }

    public void calculate(double x) {
        switch (lastCommand) {
            case "+": result += x; break;
            case "-": result -= x; break;
            case "×": result *= x; break;
            case "÷": result /= x; break;
            case "=": result = x; break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}