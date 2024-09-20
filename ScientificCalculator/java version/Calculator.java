import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JApplet implements ActionListener {
    private JTextField display;
    private double result = 0;
    private String lastCommand = "=";
    private boolean start = true;

    public void init() {
        setLayout(new BorderLayout());
        display = new JTextField("0");
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,7));

        String[] buttonLabels = {
            "Rad", "Deg", "x!", "(", ")", "%", "AC",
            "Inv", "sin", "ln", "7", "8", "9", "÷",
            "π", "cos", "log", "4", "5", "6", "×",
            "e", "tan", "√", "1", "2", "3", "-",
            "Ans", "EXP", "x^y", "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (start) {
                display.setText(command);
                start = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else {
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else {
                    lastCommand = command;
                }
            } else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    public void calculate(double x) {
        switch (lastCommand) {
            case "+": result += x; break;
            case "-": result -= x; break;
            case "×": result *= x; break;
            case "÷": result /= x; break;
            case "=": result = x; break;
            case "sin": result = Math.sin(x); break;
            case "cos": result = Math.cos(x); break;
            case "tan": result = Math.tan(x); break;
            case "log": result = Math.log10(x); break;
            case "ln": result = Math.log(x); break;
            case "√": result = Math.sqrt(x); break;
            case "x!": result = factorial(x); break;
            case "%": result = x / 100; break;
            case "x^y": result = Math.pow(result, x); break;
        }
        display.setText("" + result);
    }

    private double factorial(double n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scientific Calculator");
        Calculator calculator = new Calculator();
        calculator.init();
        frame.add(calculator);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}