package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    final JTextField usernameInput = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInput = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton signUp;

    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        this.loginViewModel = loginViewModel;
        this.loginController = controller;
        loginViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(loginViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(loginViewModel.USERNAME_LABEL), usernameInput);
        usernameInfo.setAlignmentY(Component.CENTER_ALIGNMENT);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(loginViewModel.PASSWORD_LABEL), passwordInput);
        passwordInfo.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        signUp = new JButton(LoginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(logIn);
        buttons.add(signUp);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(logIn)){
                            LoginState currentState = loginViewModel.getLoginState();
                            if(checkUsername() && checkPassword()){
                                currentState.setUsername(usernameInput.getText());
                                currentState.setPassword(passwordInput.getText());
                                loginViewModel.setLoginState(currentState);
                                loginController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword()
                                );
                            }
                        }
                    }
                }
        );

        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginController.signup_view();
                        System.out.println("Clicked Sign Up");
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if(state.getUsernameError() != null){
            JOptionPane.showMessageDialog(this,
                    state.getUsernameError(),"Username Error", JOptionPane.ERROR_MESSAGE);
        }
        if (state.getPasswordError() != null){
            JOptionPane.showMessageDialog(this,
                    state.getPasswordError(),"Password Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean checkUsername(){
        if(usernameInput.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter a valid username");
            return false;
        }
        return true;
    }
    private boolean checkPassword(){
        if(passwordInput.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter password");
            return false;
        }
        return true;
    }
}
