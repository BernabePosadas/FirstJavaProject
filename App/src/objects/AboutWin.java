package objects;

import javax.swing.*;
import java.awt.*;

public class AboutWin extends JDialog{
	private final ImageIcon background = new ImageIcon("AboutWinBack.png");
        private final ImageIcon icon = new ImageIcon("icon.png");
	private final ImageIcon HuntersGuildBanner = new ImageIcon("HuntersGuildBannerSmall.png");
	private final ImageIcon aboutMascot = new ImageIcon("aboutMascot.png");
	private final ImageIcon nekopara = new ImageIcon("nekopara.png");
	private final ImageIcon capcom = new ImageIcon("CapcomLogo.png");
	private final JLabel back = new JLabel(background);
	private final JLabel nekoparaIcon = new JLabel(nekopara);
	private final JLabel capcomLogo = new JLabel(capcom);
	private final JLabel girl = new JLabel(aboutMascot);
	private final JLabel banner = new JLabel(HuntersGuildBanner);
	private final JLabel aboutlbl = new JLabel("About");
	private final JLabel message1 = new JLabel("The Hunters guild is a great place to stay when you are");
	private final JLabel message2 = new JLabel("searching for friends. Its place where you can meet up");
	private final JLabel message3 = new JLabel("with other hunters who happen to be on the same both");
	private final JLabel message4 = new JLabel("as you so why not join the Guild and start your adventure");
	private final JLabel message5 = new JLabel("with your newly appointed comrades in arms!!");
	private final JLabel message6 = new JLabel("We dont own all images, copyright goes to their respective owners:");
	private final JLabel message7 = new JLabel("NEKOWORKS AND CAPCOM");
	private final JLabel message8 = new JLabel("Characters belong to: Nekoworks");
	private final JLabel message9 = new JLabel("Background and icons: CAPCOM");
	private final JLabel message10 = new JLabel("Created by:");
	private final JLabel message11 = new JLabel("Bernabe Posadas Jr.");
	private final JLabel message12 = new JLabel("Johnivan De Mesa");

    public AboutWin(){
    	setModal(true);
    	this.setTitle("About Hunter's Guild");
    	this.setSize(600, 350);
    	this.setLocation(370, 230);
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setResizable(false);
    	this.content();
    	setIconImage(this.icon.getImage());
    	setVisible(true);
    }
    private void content(){
     	Container con = this.getContentPane();
    	con.setLayout(null);
    	Font aboutFont = new Font("Arial", Font.PLAIN, 24);
    	Font messageFont1 = new Font("Arial", Font.PLAIN, 15);
    	Font messageFont2 = new Font("Arial", Font.BOLD, 12);
    	Font messageFont3 = new Font(null, Font.PLAIN, 12);
    	Font messageFont4 = new Font("Arial", Font.PLAIN, 12);
    	aboutlbl.setBounds(20, 30, 100, 30);
    	aboutlbl.setFont(aboutFont);
    	aboutlbl.setForeground(new Color(255, 255, 255));
    	banner.setBounds(85, 20, 280, 57);
    	girl.setBounds(460, 30, 120, 120);
    	nekoparaIcon.setBounds(280, 230, 150, 83);
    	capcomLogo.setBounds(440, 270, 150, 39);
    	message1.setBounds(20, 75, 360, 20);
    	message1.setFont(messageFont1);
    	message1.setForeground(new Color(255, 255, 255));
    	message2.setBounds(20, 95, 360, 20);
    	message2.setFont(messageFont1);
    	message2.setForeground(new Color(255, 255, 255));
    	message3.setBounds(20, 115, 360, 20);
    	message3.setFont(messageFont1);
    	message3.setForeground(new Color(255, 255, 255));
    	message4.setBounds(20, 135, 370, 20);
    	message4.setFont(messageFont1);
    	message4.setForeground(new Color(255, 255, 255));
    	message5.setBounds(20, 155, 360, 20);
    	message5.setFont(messageFont1);
    	message5.setForeground(new Color(255, 255, 255));
    	message6.setBounds(20, 190, 230, 15);
    	message6.setFont(messageFont2);
    	message6.setForeground(new Color(255, 255, 255));
    	message7.setBounds(60, 210, 230, 12);
    	message7.setFont(messageFont4);
    	message7.setForeground(new Color(255, 255, 255));
    	message8.setBounds(20, 225, 400, 15);
    	message8.setFont(messageFont3);
    	message8.setForeground(new Color(255, 255, 255));
    	message9.setBounds(20, 240, 230, 14);
    	message9.setFont(messageFont4);
    	message9.setForeground(new Color(255, 255, 255));
    	message10.setBounds(20, 260, 230, 14);
    	message10.setFont(messageFont4);
    	message10.setForeground(new Color(255, 255, 255));
    	message11.setBounds(20, 275, 230, 14);
    	message11.setFont(messageFont4);
    	message11.setForeground(new Color(255, 255, 255));
    	message12.setBounds(20, 290, 230, 14);
    	message12.setFont(messageFont4);
    	message12.setForeground(new Color(255, 255, 255));
    	back.setBounds(0, 0, this.getWidth(), this.getHeight());
    	
    	con.add(aboutlbl);
    	con.add(message1);
    	con.add(message2);
    	con.add(message3);
    	con.add(message4);
    	con.add(message5);
    	con.add(message6);
    	con.add(message7);
    	con.add(message8);
    	con.add(message9);
    	con.add(message10);
    	con.add(message11);
    	con.add(message12);
    	con.add(banner);
    	con.add(girl);
    	con.add(nekoparaIcon);
    	con.add(capcomLogo);
    	con.add(back);
    }
    
}