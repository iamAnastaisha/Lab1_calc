import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

class Calc implements ActionListener {
    JFrame jFrame;
    JTextField jTextField1, jTextField2, jTextField3, jTextField4;
    JLabel jLabel, jLabelResult, jLabelOperands;
    JComboBox jComboBox1, jComboBox2, jComboBox3, jComboBoxOperands;
    JButton beq, bclr;
    String aString = "", bString = "", cString = "", dString = "";

    boolean isDividingByZero = false;

    static BigDecimal a = new BigDecimal("0"), b = new BigDecimal("0"),
            c = new BigDecimal("0"), d = new BigDecimal("0"), result = new BigDecimal("0");
    static int operator1 = 1, operator2 = 1, operator3 = 1;
    static int numOfOperands = 2;

    Calc() {
        jFrame =new JFrame("Calculator");
        jTextField1 =new JTextField();
        jTextField2 =new JTextField();
        jTextField3 =new JTextField();
        jTextField4 =new JTextField();
        String[] items = {"+", "-", "*", "/"};
        jComboBox1 = new JComboBox(items);
        jComboBox2 = new JComboBox(items);
        jComboBox3 = new JComboBox(items);
        jLabelResult = new JLabel();
        jLabel = new JLabel("Гаркавая А. Ю., 4 курс, 4 группа, 2023 год");
        jLabelOperands = new JLabel("Select number of operands");
        items = new String[]{"2", "3", "4"};
        jComboBoxOperands = new JComboBox(items);
        beq=new JButton("=");
        bclr=new JButton("Clear");

        jLabel.setBounds(30, 0, 340, 30);
        jLabelOperands.setBounds(30, 30, 200, 30);
        jComboBoxOperands.setBounds(210, 30, 60, 30);
        jTextField1.setBounds(30,70,280,30);
        jComboBox1.setBounds(30, 110, 50, 30);
        jTextField2.setBounds(30,150,280,30);
        jComboBox2.setBounds(30, 190, 50, 30);
        jComboBox2.setVisible(false);
        jTextField3.setBounds(30,230,280,30);
        jTextField3.setVisible(false);
        jComboBox3.setBounds(30, 270, 50, 30);
        jComboBox3.setVisible(false);
        jTextField4.setBounds(30,310,280,30);
        jTextField4.setVisible(false);

        jLabelResult.setBounds(40, 340, 340, 30);
        beq.setBounds(40,400,50,40);
        bclr.setBounds(130,400,100,40);

        jFrame.add(jLabel);
        jFrame.add(jLabelOperands);
        jFrame.add(jComboBoxOperands);
        jFrame.add(jTextField1);
        jFrame.add(jComboBox1);
        jFrame.add(jTextField2);
        jFrame.add(jComboBox2);
        jFrame.add(jTextField3);
        jFrame.add(jComboBox3);
        jFrame.add(jTextField4);
        jFrame.add(jLabelResult);
        jFrame.add(beq);
        jFrame.add(bclr);

        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setSize(350,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jComboBox1.addActionListener(this);
        jComboBox2.addActionListener(this);
        jComboBox3.addActionListener(this);
        jComboBoxOperands.addActionListener(this);
        beq.addActionListener(this);
        bclr.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jComboBoxOperands) {
            numOfOperands = jComboBoxOperands.getSelectedIndex() + 2;
            switch(numOfOperands) {
                case 2 -> {
                    jComboBox2.setVisible(false);
                    jTextField3.setVisible(false);
                    jComboBox3.setVisible(false);
                    jTextField4.setVisible(false);
                }
                case 3 -> {
                    jComboBox2.setVisible(true);
                    jTextField3.setVisible(true);
                    jComboBox3.setVisible(false);
                    jTextField4.setVisible(false);
                }
                case 4 -> {
                    jComboBox2.setVisible(true);
                    jTextField3.setVisible(true);
                    jComboBox3.setVisible(true);
                    jTextField4.setVisible(true);
                }
            }
        }

        if(e.getSource()==jComboBox1) {
            operator1 = jComboBox1.getSelectedIndex() + 1;
        }

        if(e.getSource()==jComboBox2) {
            operator2 = jComboBox2.getSelectedIndex() + 1;
        }

        if(e.getSource()==jComboBox3) {
            operator3 = jComboBox3.getSelectedIndex() + 1;
        }

        if(e.getSource()==beq) {
            aString = jTextField1.getText();
            bString = jTextField2.getText();
            boolean isOk = StringProcessing.isStringOk(jFrame, aString, 1) && StringProcessing.isStringOk(jFrame, bString, 2);
            if (numOfOperands == 3) {
                cString = jTextField3.getText();
                isOk = StringProcessing.isStringOk(jFrame, aString, 1)
                        && StringProcessing.isStringOk(jFrame, bString, 2)
                        && StringProcessing.isStringOk(jFrame, cString, 3);
            } else if (numOfOperands == 4) {
                cString = jTextField3.getText();
                dString = jTextField4.getText();
                isOk = StringProcessing.isStringOk(jFrame, aString, 1)
                        && StringProcessing.isStringOk(jFrame, bString, 2)
                        && StringProcessing.isStringOk(jFrame, cString, 3)
                        && StringProcessing.isStringOk(jFrame, dString, 4);
            }
            if (isOk) {
                a = new BigDecimal(StringProcessing.changeString(aString));
                b = new BigDecimal(StringProcessing.changeString(bString));
                if (numOfOperands == 3) {
                    c = new BigDecimal(StringProcessing.changeString(cString));
                } else if (numOfOperands == 4) {
                    c = new BigDecimal(StringProcessing.changeString(cString));
                    d = new BigDecimal(StringProcessing.changeString(dString));
                }

                a = a.setScale(10, RoundingMode.HALF_UP);
                b = b.setScale(10, RoundingMode.HALF_UP);
                c = c.setScale(10, RoundingMode.HALF_UP);
                d = d.setScale(10, RoundingMode.HALF_UP);
                result = result.setScale(10, RoundingMode.HALF_UP);

                if (numOfOperands >= 3) {
                    switch (operator2) {
                        case 1 -> b = b.add(c);
                        case 2 -> b = b.subtract(c);
                        case 3 -> b = b.multiply(c);
                        case 4 -> {
                            if (Objects.equals(c, BigDecimal.ZERO.setScale(10))) {
                                JOptionPane.showMessageDialog(jFrame, "Dividing by zero!", "Dialog",
                                        JOptionPane.ERROR_MESSAGE);
                                jLabelResult.setText("");
                                isDividingByZero = true;
                            } else {
                                b = b.setScale(10, RoundingMode.HALF_UP);
                                b = b.multiply(new BigDecimal("1.0")).divide(c, RoundingMode.HALF_UP);
                                isDividingByZero = false;
                            }
                        }
                    }
                }

                switch (operator1) {
                    case 1 -> result = a.add(b);
                    case 2 -> result = a.subtract(b);
                    case 3 -> result = a.multiply(b);
                    case 4 ->  {
                        if (Objects.equals(b, BigDecimal.ZERO.setScale(10))) {
                            JOptionPane.showMessageDialog(jFrame, "Dividing by zero!", "Dialog",
                                    JOptionPane.ERROR_MESSAGE);
                            jLabelResult.setText("");
                            isDividingByZero = true;
                        } else {
                            result = result.setScale(10,RoundingMode.HALF_UP);
                            result = a.multiply(new BigDecimal("1.0")).divide(b,RoundingMode.HALF_UP);
                            isDividingByZero = false;
                        }
                    }
                    default -> result = BigDecimal.ZERO;
                }

                if (numOfOperands == 4) {
                    switch (operator3) {
                        case 1 -> result = result.add(d);
                        case 2 -> result = result.subtract(d);
                        case 3 -> result = result.multiply(d);
                        case 4 -> {
                            if (Objects.equals(d, BigDecimal.ZERO.setScale(10))) {
                                JOptionPane.showMessageDialog(jFrame, "Dividing by zero!", "Dialog",
                                        JOptionPane.ERROR_MESSAGE);
                                jLabelResult.setText("");
                                isDividingByZero = true;
                            } else {
                                result = result.setScale(10, RoundingMode.HALF_UP);
                                result = result.multiply(new BigDecimal("1.0")).divide(d, RoundingMode.HALF_UP);
                                isDividingByZero = false;
                            }
                        }
                        default -> result = BigDecimal.ZERO;
                    }
                } if (!isDividingByZero) {
                    result = result.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros();
                    jLabelResult.setText("Result: " + StringProcessing.changeResult(result.toPlainString()));
                }
            }
        }

        if(e.getSource()==bclr) {
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jLabelResult.setText("");
            result = new BigDecimal(0);
        }
    }

    public static void main(String...s) {
        new Calc();
    }
}

