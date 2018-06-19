package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

public class Frame implements ActionListener {

    JFrame frame = new JFrame("Test");

    public void jFrameTest() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Start");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        JMenuItem runItem = new JMenuItem("Run", KeyEvent.VK_T);
        runItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        runItem.addActionListener(this);
        menu.add(runItem);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setJMenuBar(menuBar);
        //TODO auf true dann fullsscreen
        frame.setUndecorated(false);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        RenderImage.createImage();
        System.out.println("Run pressed");
    }

    public void itemStateChanged(ItemEvent e) {
        //...Get information from the item event...
        //...Display it in the text area...
    }
}
