package ru.vsu.cs.course1;

import javax.swing.*;

public class FrameMain extends JFrame{

    private JPanel panelMain;
    private JButton buttonRead;
    private JButton buttonWrite;
    private JTextField textFieldInput;

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}
