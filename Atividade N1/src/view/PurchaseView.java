package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.PurchaseController;

public class PurchaseView {
    private PurchaseController controller;
    private JFrame frame;
    private JTextField purchaseField;
    private JTextArea resultArea;
    private JButton addButton;
    private JButton showTotalButton;
    private JButton countButton;
    private JButton clearButton;

    public PurchaseView(PurchaseController controller) {
        this.controller = controller;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Sistema de Compras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null); 

        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sistema de Compras", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Valor da Compra:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        purchaseField = new JTextField(15);
        inputPanel.add(purchaseField, gbc);

        addButton = new JButton("Adicionar Compra");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(addButton, gbc);

        showTotalButton = new JButton("Mostrar Total");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(showTotalButton, gbc);

        countButton = new JButton("Contar Compras R$ 500 - R$ 700");
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(countButton, gbc);

        clearButton = new JButton("Limpar Tela");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(clearButton, gbc);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPurchase();
            }
        });

        showTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTotal();
            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countPurchasesInRange();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScreen();
            }
        });

        purchaseField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPurchase();
            }
        });

        frame.setVisible(true);
    }

    private void addPurchase() {
        try {
            double amount = Double.parseDouble(purchaseField.getText());
            controller.addPurchase(amount);
            resultArea.append("Compra de R$ " + amount + " adicionada.\n");
            purchaseField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido.");
        }
    }

    private void showTotal() {
        double total = controller.getTotal();
        resultArea.append("Total de todas as compras: R$ " + total + "\n");
    }

    private void countPurchasesInRange() {
        int count = controller.countPurchasesInRange(500, 700);
        resultArea.append("Número de compras entre R$ 500 e R$ 700: " + count + "\n");
    }

    private void clearScreen() {
        controller.clearPurchases();
        resultArea.setText("");
        purchaseField.setText("");
    }
}
