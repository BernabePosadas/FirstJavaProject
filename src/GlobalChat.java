import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;

public class GlobalChat extends JFrame implements ActionListener{
	Rectangles rect1 = new Rectangles();
	ImageIcon Handler = new ImageIcon("GlobalChatHandler.png");
	ImageIcon vanira = new ImageIcon("vanilla.png");
	ImageIcon background = new ImageIcon("GlobalChatBack.png");
	ImageIcon vanillaSpeechBubble = new ImageIcon("VanillaSpeechBubble.png");
	JLabel Speech = new JLabel(vanillaSpeechBubble);
	JLabel globalChatHandler = new JLabel(Handler);
	JLabel vanilla = new JLabel(vanira);
	JLabel back = new JLabel(background);
	JLabel loglbl = new JLabel("Global Chat Log:");
	JTextField message = new JTextField();
	DefaultListModel mod = new DefaultListModel();
	JList chatArea = new JList(mod);
	JScrollPane scr = new JScrollPane(chatArea);
	JButton ret = new JButton("Return To Home");
	JButton send = new JButton("Send");

    public GlobalChat() {
    	this.setTitle("Hunter's Guild: Global Chat");
    	this.setSize(1330, 715);
    	this.setLocation(20, 10);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	content();
    	getLog();
    	setIconImage(ProgramUser.icon.getImage());
    	setVisible(true);
    }
    void getLog(){
    	IOFileStream io = new IOFileStream();
    	String[] getMessages = io.getGlobalChatLog();
    	if(getMessages != null){
    		for(int i = 0; i < getMessages.length; i++){
    			mod.addElement(getMessages[i]);
    		}
    		chatArea.setSelectedIndex(mod.size() - 1);
    	}
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == ret){
    		ProgramUser.ViewingUser = ProgramUser.CurrentUser;
    		new ProfileViewer();
    		dispose(); 
    	}else if(e.getSource() == send){
    		String getMessage = message.getText();
    		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YY hh:mm");
    		Calendar cld = Calendar.getInstance();
    		mod.addElement("[" + fmt.format(cld.getTime()) + "]" + ProgramUser.CurrentUser + ": " + getMessage);
    		chatArea.setSelectedIndex(mod.size() - 1);
    		String getAllMessages = "";
    		for(int i = 0; i < mod.size(); i++){
    			getAllMessages += mod.getElementAt(i) + "\r\n";
    		}
    		IOFileStream io = new IOFileStream();
    		message.setText("");
    		io.saveGlobalChatLog(getAllMessages);
    	}
    }
    void content(){
    	Container con = getContentPane();
    	con.setLayout(null);
    	Font LoglblFont = new Font("Comic Sans MS", Font.BOLD, 16);
    	loglbl.setFont(LoglblFont);
    	loglbl.setBounds(40, 40, 150, 20);
    	scr.setBounds(40, 60, 480, 500);
    	message.setBounds(40, 570, 380, 30);
    	send.setBounds(420, 570, 100, 30);
    	ret.setBounds(40, 610, 480, 30);
    	back.setBounds(0, 0, this.getWidth(), this.getHeight());
    	vanilla.setBounds(this.getWidth() - 583, 0, 583, 720);
    	globalChatHandler.setBounds(10, 10, 540, 665);
    	Speech.setBounds(580, 10, 400, 200);
    	
    	ret.addActionListener(this);
    	send.addActionListener(this);
    	
    	con.add(loglbl);
    	con.add(scr);
    	con.add(message);
    	con.add(send);
    	con.add(ret);
    	con.add(Speech);
    	con.add(globalChatHandler);
    	con.add(vanilla);
    	con.add(back);
    }
    
}