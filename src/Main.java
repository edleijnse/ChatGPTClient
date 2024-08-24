import forms.ChatGPTFormSimple;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Launch the book editor form
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatGPTFormSimple chatGPTForm = new ChatGPTFormSimple();
                chatGPTForm.setVisible(true);

            }
        });
    }
}