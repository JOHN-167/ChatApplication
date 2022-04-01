import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

public class ChatApplication extends JFrame {
    //constructor
    public ChatApplication(){
        setSize(600,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(new FlowLayout());

        //chat gui
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(Color.PINK);
        add(bgPanel);

        /*
        JComponent loginComponent = new JComponent(){
            @Override
            public void paintComponent(Graphics g) {}
        };
        JComponent placeholder = new JComponent(){
            @Override
            public void paintComponent(Graphics g) {}
        };
        placeholder.setMaximumSize(new Dimension(200,150));
        bgPanel.add(placeholder);
        loginComponent.setFocusable(true);
        loginComponent.setLayout(new BoxLayout(loginComponent, BoxLayout.Y_AXIS));
        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.add(userLabel);
        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(200,30));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setVisible(true);
        loginComponent.add(username);
        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.add(passLabel);
        JTextField password = new JTextField();
        password.setMaximumSize(new Dimension(200,30));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setVisible(true);
        loginComponent.add(password);
        bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.PAGE_AXIS));
        // loginComponent.setAlignmentY(Component.CENTER_ALIGNMENT);
        loginComponent.setPreferredSize(new Dimension(200,150));
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                String user = "", pass = "";
                Boolean successful = false;
                try {
                    FileReader file = new FileReader("loginDetails.txt");
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine() && successful == false){
                        String input = scan.nextLine(); 
                        Scanner sc = new Scanner(input);
                        sc.useDelimiter("\t");
                        user = sc.next();
                        pass = sc.next();
                        System.out.printf("User = %s, Pass = %s%n",user,pass);
                        if (user.equals(username.getText()) &&
                            pass.equals(password.getText())) {
                            successful = true;
                        }
                        sc.close();
                    }
                    scan.close();
                } catch (FileNotFoundException e){
                    System.out.println(e);
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("NoMatch");
                }
                if (successful == true){
                    bgPanel.remove(loginComponent);
                    bgPanel.revalidate();
                    bgPanel.repaint();
                } else {
                    username.setText("");
                    password.setText("");
                }
            }
        }); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginComponent.add(loginButton);
        bgPanel.add(loginComponent);
        */
    
        bgPanel.setPreferredSize(new Dimension(getWidth(),getHeight()-80));
        JComponent chat = new JComponent(){
            @Override
            public void paintComponent(Graphics g) {}
        };
        chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
        bgPanel.add(chat);
        try {
            FileReader file = new FileReader("chatHistory.txt");
                Scanner scan = new Scanner(file);
                
                while (scan.hasNextLine()){
                    JComponent filler = new JComponent(){
                        @Override
                        public void paintComponent(Graphics g) {}
                    };
                    filler.setPreferredSize(new Dimension(20,20));
                    String input = scan.nextLine();
                    JLabel chatText = new JLabel(input);
                    filler.setAlignmentX(Component.RIGHT_ALIGNMENT);
                    chatText.setAlignmentX(Component.RIGHT_ALIGNMENT);
                    chat.add(chatText,0);
                    chat.add(filler,0);
                }
                scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTextField chatField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    FileWriter chatHistory = new FileWriter("chatHistory.txt");
                    chatHistory.write(chatField.getText());
                    chatHistory.close();
                    chatField.setText("");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        chatField.setColumns(40);
        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.WHITE);
        chatPanel.add(chatField);
        chatPanel.add(sendButton);
        chatPanel.setPreferredSize(new Dimension(getWidth(),80));
        add(chatPanel);
        
        
    }
    public static void main(String[] args){
        new ChatApplication().setVisible(true);
    }
}