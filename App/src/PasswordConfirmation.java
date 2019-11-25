import Objects.ProgramUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PasswordConfirmation extends JDialog implements ActionListener{
	JLabel lblPass = new JLabel("Password:");
	JPasswordField pass = new JPasswordField();
	JButton btnOk = new JButton("OK");

    public PasswordConfirmation() {
    	this.setModal(true);
    	this.setTitle("Password Confirmation");
    	this.setSize(300, 120);
    	this.setLocation(470, 250);
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setResizable(false);
    	content();
    	setIconImage(ProgramUser.icon.getImage());
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == btnOk){
    		IOFileStream io = new IOFileStream();
    		boolean checkPass = io.checkPass(pass.getText());
    		if(checkPass){
    			ProgramUser.PasswordConfirmationReply = true;
    			dispose();
    		}else{
    			JOptionPane.showMessageDialog(null, "Password Incorrect", "Change Password" , JOptionPane.ERROR_MESSAGE);
    			ProgramUser.PasswordConfirmationReply = false;
    			dispose();
    		}
    	}
    }
    void content(){
    	Container con = getContentPane();
    	con.setLayout(null);
    	lblPass.setBounds(20, 10, 100, 20);
    	pass.setBounds(20, 30, 250, 20);
    	btnOk.setBounds(110, 60, 70, 20);
    	btnOk.addActionListener(this);
    	
    	con.add(lblPass);
    	con.add(pass);
    	con.add(btnOk);
    	
    }
    
    
}