import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class ChatApplication extends JFrame {
    //constructor
    public ChatApplication(){
        setSize(600,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);

        //chat gui
        JPanel loginComponent = new JPanel();
        // loginComponent.setBackground(Color.WHITE);
        loginComponent.setFocusable(true);
        loginComponent.setLayout(new BoxLayout(loginComponent, BoxLayout.Y_AXIS));
        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.add(userLabel);
        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(100,30));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setVisible(true);
        loginComponent.add(username);
        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.add(passLabel);
        JTextField password = new JTextField();
        password.setMaximumSize(new Dimension(100,30));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setVisible(true);
        loginComponent.add(password);
        JPanel loginPage = new JPanel();
        loginPage.setBackground(Color.PINK);
        loginPage.setLayout(new BoxLayout(loginPage, BoxLayout.PAGE_AXIS));
        // loginComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.setAlignmentY(Component.CENTER_ALIGNMENT);
        // loginComponent.setPreferredSize(new Dimension(200,150));
        loginPage.add(loginComponent);
        add(loginPage);
    }
    public static void main(String[] args){
        new ChatApplication().setVisible(true);
    }
}
