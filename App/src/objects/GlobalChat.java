package objects;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;

public class GlobalChat extends JFrame implements ActionListener{
	private final ImageIcon Handler = new ImageIcon("GlobalChatHandler.png");
	private final ImageIcon vanira = new ImageIcon("vanilla.png");
	private final ImageIcon background = new ImageIcon("GlobalChatBack.png");
	private final ImageIcon vanillaSpeechBubble = new ImageIcon("VanillaSpeechBubble.png");
        private final ImageIcon icon = new ImageIcon("icon.png");
	private final JLabel Speech = new JLabel(vanillaSpeechBubble);
	private final JLabel globalChatHandler = new JLabel(Handler);
	private final JLabel vanilla = new JLabel(vanira);
	private final JLabel back = new JLabel(background);
	private final JLabel loglbl = new JLabel("Global Chat Log:");
	private final JTextField message = new JTextField();
	private final DefaultListModel mod = new DefaultListModel();
	private final JList chatArea = new JList(mod);
	private final JScrollPane scr = new JScrollPane(chatArea);
	private final JButton ret = new JButton("Return To Home");
	private final JButton send = new JButton("Send");
    public GlobalChat() {
    	this.setTitle("Hunter's Guild: Global Chat");
    	this.setSize(1330, 715);
    	this.setLocation(20, 10);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	this.content();
    	this.getLog();
    	setIconImage(this.icon.getImage());
    	setVisible(true);
    }
    private void getLog(){
    	IOFileStream io = new IOFileStream();
    	String[] getMessages = io.getGlobalChatLog();
    	if(getMessages != null){
                for (String message1 : getMessages) {
                    mod.addElement(message1);
                }
    		chatArea.setSelectedIndex(mod.size() - 1);
    	}
    }
    @Override
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == ret){
    		new ProfileViewer(UserSession.CurrentUser);
    		dispose(); 
    	}else if(e.getSource() == send){
    		String getMessage = message.getText();
    		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YY hh:mm:ss");
    		Calendar cld = Calendar.getInstance();
    		mod.addElement("[" + fmt.format(cld.getTime()) + "]" + UserSession.CurrentUser.UserID + ": " + getMessage);
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
    private void content(){
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