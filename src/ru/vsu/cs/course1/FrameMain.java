package ru.vsu.cs.course1;

import ru.vsu.cs.utils.JTableUtils;
import ru.vsu.cs.utils.SwingUtils;
import ru.vsu.cs.utils.ArrayUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrameMain extends JFrame{

    private JPanel panelMain;
    private JButton buttonRead;
    private JButton buttonWrite;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonSolve;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
        //tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JTableUtils.writeArrayToJTable(tableInput, new int[][]{
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        });

        this.pack();

        buttonRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, arr);
                    }

                } catch(Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = JTableUtils.readIntMatrixFromJTable(tableOutput);
                        ArrayUtils.writeArrayToFile(fileChooserSave.getSelectedFile().getPath(), arr);
                    }

                } catch(Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] arr = JTableUtils.readIntMatrixFromJTable(tableInput);
                    arr = Task.solve(arr);
                    JTableUtils.writeArrayToJTable(tableOutput, arr);

                } catch(Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
