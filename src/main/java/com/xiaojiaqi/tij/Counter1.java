package com.xiaojiaqi.tij;

/**
 * @Author: liangjiaqi
 * @Date: 2020/4/16 1:10 PM
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class Counter1 extends Applet {
    int count = 0;
    Button
            onOff = new Button("Toggle"),
            start = new Button("Start");
    TextField t = new TextField(10);
    boolean runFlag = true;
    @Override
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }
    public void go() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){}
            if(runFlag) {
                t.setText(Integer.toString(count++));
            }
        }
    }
    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }
    class OnOffL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }
    static class WL extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
    public static void main(String args[]) {
        Counter1 applet = new Counter1();
        Frame aFrame = new Frame("Counter1");
        aFrame.addWindowListener(new WL());
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}