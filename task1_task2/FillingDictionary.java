package com.gmail.malynovskyiroman.javaOOP.map.task1_task2;

import javax.swing.*;
import java.awt.*;

public class FillingDictionary extends JFrame {

    private String pathToDictionary;
    private Container container;
    private JLabel l1;
    private JLabel l2;
    private JTextField eng;
    private JTextField ukr;
    private JButton ok;
    private JButton clear;
    private JButton exit;
    private EngUkrDictionary engUkrDictionary;

    public FillingDictionary(EngUkrDictionary engUkrDictionary, String pathToDictionary) {
        super("Filling Dictionary");
        this.engUkrDictionary = engUkrDictionary;
        this.pathToDictionary = pathToDictionary;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new FlowLayout());
        l1 = new JLabel("Word in English");
        l2 = new JLabel("Word in Ukrainian");
        eng = new JTextField(10);
        ukr = new JTextField(10);
        ok = new JButton("OK");
        clear = new JButton("Clear");
        exit = new JButton("Exit");
        container.add(l1);
        container.add(l2);
        container.add(eng);
        container.add(ukr);
        container.add(ok);
        container.add(clear);
        container.add(exit);
        setVisible(true);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        addListener();
    }

    public void addListener() {
        exit.addActionListener(e -> {
            if (e.getSource() == exit) {
                engUkrDictionary.saveToFile(pathToDictionary);
                System.exit(0);
        }
        });
        clear.addActionListener(e -> {
            if (e.getSource() == clear) {
                eng.setText(null);
                ukr.setText(null);
            }
        });
        ok.addActionListener(e -> {
            if (e.getSource() == ok) {
                if (eng.getText().matches("\\s+") || ukr.getText().matches("\\s+") || eng.getText().equals("")
                        || ukr.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please, specify both fields!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                } else {
                    engUkrDictionary.getMap().putIfAbsent(eng.getText(), ukr.getText());
                }
            }
        });
    }
}
