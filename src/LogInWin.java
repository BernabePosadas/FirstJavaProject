import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LogInWin extends JFrame implements ActionListener {
	ImageIcon background = new ImageIcon("LogInBack.png");
	ImageIcon banner = new ImageIcon("HuntersGuildBanner.png");
	ImageIcon LogInHandler = new ImageIcon("LogInHandler.png");
	JLabel handler = new JLabel(LogInHandler);
	JLabel ban = new JLabel(banner);
	JLabel back = new JLabel(background);
	Rectangles rect1 = new Rectangles();
	JLabel username = new JLabel("Username:");
	JTextField usern = new JTextField();
	JLabel password = new JLabel("Password:");
	JPasswordField pass = new JPasswordField();
	JButton b1 = new JButton("Log In");
	JButton b2 = new JButton("Register");
	JButton b3 = new JButton("Exit");
	JButton AboutButton = new JButton("About Hunter's Guild");

    public LogInWin() {
    	this.setTitle("Hunter's Guild Log In");
    	this.setSize(1100, 700);
    	this.setLocation(150, 20);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	content();
    	setIconImage(ProgramUser.icon.getImage());
    	this.setVisible(true);
    	
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == b1){
    		IOFileStream io = new IOFileStream();
    		boolean getReply = io.logIn(usern.getText(), pass.getText());
    		if(getReply){
    			String getUser = usern.getText().substring(0, 1).toUpperCase() + usern.getText().substring(1).toLowerCase();
    			ProgramUser.CurrentUser = getUser;
    			ProgramUser.ViewingUser = getUser;
    			new ProfileViewer();
    			dispose();
    		}
    		pass.setText("");
    	}
    	else if(e.getSource() == b2){
    		new Register();
    		dispose();
    	}else if(e.getSource() == b3){
    		System.exit(0);
    	}else if(e.getSource() == AboutButton){
    		new AboutWin();
    	}
    	 
    }
    void content(){
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