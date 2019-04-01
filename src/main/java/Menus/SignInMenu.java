package Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInMenu extends JFrame {
    private JPanel mainPanel;
    private JButton signInButton;
    private JButton backButton;
    private JTextField usernameTextField;
    private JTextField passwordTextField;

    protected SignInMenu() {
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn.username = usernameTextField.getText();
                SignIn.password = passwordTextField.getText();
                SignIn.go = true;
                SignIn.hold = false;
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu gui = new MainMenu();
                dispose();
            }
        });
    }
}
