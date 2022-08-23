
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
    public static void main(String[] args) {
        Mainframe frame = new Mainframe();
    }
}

class Mainframe extends JFrame {
    private JButton btnPantalla;
    boolean startValue = true;
    private double result = 0;
    private String lastOperation = "=";


    public Mainframe() {
        super("Calculadora");
        setSize(320, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        PanelPantalla panelPantalla = new PanelPantalla();
        add(panelPantalla, BorderLayout.NORTH);
        PanelTeclado panelTeclado = new PanelTeclado();
        add(panelTeclado, BorderLayout.CENTER);

        //pack();
    }
    class PanelPantalla extends JPanel {

        public PanelPantalla() {
            setLayout(new BorderLayout());
            btnPantalla = new JButton("0");
            btnPantalla.setEnabled(false);
            add(btnPantalla, BorderLayout.NORTH);
        }
    }

    class NumberListener implements ActionListener {
        /* Para ejecutar una instrucción una única vez en un método que se ejecuta en múltiples ocasiones, se utiliza una variable booleana junto a una estructura condicional. */
        String input = "";
        public void actionPerformed(ActionEvent e) {
            /* El método .getActionCommand() de ActionEvent retorna el nombre, texto o valor de un input. */
            if (e.getActionCommand().equals("/") || e.getActionCommand().equals("*") || e.getActionCommand().equals("-") || e.getActionCommand().equals("+") || e.getActionCommand().equals("=")) return;
            //input += e.getActionCommand();
            if (startValue) {
                btnPantalla.setText("");
                startValue = false;
            }
            input = e.getActionCommand();
            btnPantalla.setText(btnPantalla.getText() + input);

        }
    }

    class NumberActionListener implements ActionListener {
        String operation = "";
        @Override
        public void actionPerformed(ActionEvent e) {
            /* Echarle cabeza a esta parte. */
            operation = e.getActionCommand();

            calculate(Double.parseDouble(btnPantalla.getText()));
            lastOperation = operation;

            startValue = true;
        }

        public void calculate(Double num){
            switch (lastOperation) {
                case "+" -> result += num;
                case "-" -> result -= num;
                case "*" -> result *= num;
                case "/" -> result /= num;
                case "=" -> result = num;
            }
            btnPantalla.setText(String.valueOf(result));
        }
    }

    class PanelTeclado extends JPanel {
        private final ActionListener numberListener = new NumberListener();
        private final ActionListener actionListener = new NumberActionListener();

        public PanelTeclado() {
            setLayout(new GridLayout(4, 4));
            addButton("7", numberListener);
            addButton("8", numberListener);
            addButton("9", numberListener);
            addButton("/", actionListener);
            addButton("4", numberListener);
            addButton("5", numberListener);
            addButton("6", numberListener);
            addButton("*", actionListener);
            addButton("1", numberListener);
            addButton("2", numberListener);
            addButton("3", numberListener);
            addButton("-", actionListener);
            addButton("0", numberListener);
            addButton(".", numberListener);
            addButton("=", actionListener);
            addButton("+", actionListener);
        }
        private void addButton(String text, ActionListener listener) {
            JButton button = new JButton(text);
            button.addActionListener(listener);
            add(button);
        }
    }

}




