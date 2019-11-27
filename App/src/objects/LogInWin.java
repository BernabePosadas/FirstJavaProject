package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInWin extends JFrame implements ActionListener {

    private final ImageIcon background = new ImageIcon("LogInBack.png");
    private final ImageIcon banner = new ImageIcon("HuntersGuildBanner.png");
    private final ImageIcon LogInHandler = new ImageIcon("LogInHandler.png");
    private final ImageIcon icon = new ImageIcon("icon.png");
    private final JLabel handler = new JLabel(LogInHandler);
    private final JLabel ban = new JLabel(banner);
    private final JLabel back = new JLabel(background);
    private final JLabel username = new JLabel("Username:");
    private final JTextField usern = new JTextField();
    private final JLabel password = new JLabel("Password:");
    private final JPasswordField pass = new JPasswordField();
    private final JButton b1 = new JButton("Log In");
    private final JButton b2 = new JButton("Register");
    private final JButton b3 = new JButton("Exit");
    private final JButton AboutButton = new JButton("About Hunter's Guild");

    public LogInWin() {
        this.setTitle("Hunter's Guild Log In");
        this.setSize(1100, 700);
        this.setLocation(150, 20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.content();
        this.setIconImage(this.icon.getImage());
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            IOFileStream io = new IOFileStream();
            boolean getReply = io.logIn(usern.getText(), pass.getText());
            if (getReply) {
                UserSession UserSession = new UserSession();
                String getUser = usern.getText().substring(0, 1).toUpperCase() + usern.getText().substring(1).toLowerCase();
                UserSession.CurrentUser.UserID = getUser;
                new ProfileViewer(UserSession.CurrentUser);
                dispose();
            }
            pass.setText("");
        } else if (e.getSource() == b2) {
            new Register();
            dispose();
        } else if (e.getSource() == b3) {
            System.exit(0);
        } else if (e.getSource() == AboutButton) {
            new AboutWin();
        }

    }

    private void content() {
        Container con = getContentPane();
        con.setLayout(null);
        back.setBounds(0, 0, this.getWidth(), this.getHeight());
        ban.setBounds(200, 100, 702, 134);
        username.setBounds(380, 290, 100, 20);
        usern.setBounds(380, 310, 310, 20);
        password.setBounds(380, 330, 100, 20);
        pass.setBounds(380, 350, 310, 20);
        b1.setBounds(360, 380, 100, 20);
        b2.setBounds(470, 380, 150, 20);
        b3.setBounds(630, 380, 100, 20);
        AboutButton.setBounds(445, 450, 200, 20);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        AboutButton.addActionListener(this);
        handler.setBounds(150, 240, 800, 260);

        con.add(username);
        con.add(usern);
        con.add(password);
        con.add(pass);
        con.add(b1);
        con.add(b2);
        con.add(b3);
        con.add(AboutButton);
        con.add(handler);
        con.add(ban);
        con.add(back);
    }

}
