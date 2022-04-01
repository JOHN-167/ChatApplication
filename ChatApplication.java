import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.io.*;
import java.util.List;

public class ChatApplication extends JFrame {
    //constructor
    List<String> list = printTen("ChatHistory.txt");
    JSlider slider = new JSlider(JSlider.VERTICAL,0,Math.max(list.size()-10,10),0);

    protected boolean loggedIn = false;
    public ChatApplication(){
        setSize(600,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(new FlowLayout());

        //chat gui
        class BgPanel extends JPanel {
            int sliderValue = 0;
            List<String> list;
            BgPanel(int sliderValue, List<String> list) {
                this.sliderValue = sliderValue;
                this.list = new ArrayList<>(list);
            }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(loggedIn == true){
                    for (int i = 0; i < 10; i++){
                        JComponent filler = new JComponent(){
                            @Override
                            public void paintComponent(Graphics g) {}
                        };
                        filler.setPreferredSize(new Dimension(20,20));
                        JLabel chatText = new JLabel(list.get(sliderValue+i));
                        filler.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        chatText.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        this.add(chatText,0);
                        this.add(filler,0);
                    }
                }
            }
        };
        BgPanel bgPanel = new BgPanel(slider.getValue(),list);
        bgPanel.setBackground(Color.PINK);
        bgPanel.setLayout(new BoxLayout(bgPanel,BoxLayout.Y_AXIS));
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
                        sc.useDelimiter(",");
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
    
        bgPanel.setPreferredSize(new Dimension(getWidth(),getHeight()-160));
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()){
                    bgPanel.revalidate();
                    bgPanel.repaint();
                }
            }
        });
        
        /*
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
        */
        JTextField chatField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a) {
                System.out.println("Send Button pressed");
                if (!chatField.getText().isEmpty() && !chatField.getText().isBlank()){
                    try {
                        FileWriter chatHistory = new FileWriter("chatHistory.txt",true);
                        chatHistory.append("\n" + chatField.getText());
                        chatHistory.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    list.add(chatField.getText());
                    bgPanel.add(new JLabel(chatField.getText(),0));
                    JComponent filler = new JComponent(){
                        @Override
                        public void paintComponent(Graphics g) {}
                    };
                    bgPanel.add(filler,0);
                    chatField.setText("");
                    bgPanel.revalidate();
                    bgPanel.repaint();
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
        add(slider);
        bgPanel.revalidate();
        bgPanel.repaint();        
    }

    public static List<String> printTen (String fileName){
        List<String> output = new ArrayList<>();
        try {
            FileReader file = new FileReader(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String input = scan.nextLine();
                output.add(input);
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return output;
    }
    public static void main(String[] args){
        new ChatApplication().setVisible(true);
    }
}