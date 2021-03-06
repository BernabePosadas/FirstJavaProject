package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordConfirmation extends JDialog implements ActionListener {

    private final JLabel lblPass = new JLabel("Password:");
    private final JPasswordField pass = new JPasswordField();
    private final JButton btnOk = new JButton("OK");
    private final ImageIcon icon = new ImageIcon("icon.png");
    public boolean PasswordConfirmationReply = false;

    public PasswordConfirmation() {
        this.setModal(true);
        this.setTitle("Password Confirmation");
        this.setSize(300, 120);
        this.setLocation(470, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.content();
        this.setIconImage(this.icon.getImage());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            IOFileStream io = new IOFileStream();
            boolean checkPass = io.checkPass(pass.getText());
            if (checkPass) {
                this.PasswordConfirmationReply = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Password Incorrect", "Change Password", JOptionPane.ERROR_MESSAGE);
                this.PasswordConfirmationReply = false;
                dispose();
            }
        }
    }

    private void content() {
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
