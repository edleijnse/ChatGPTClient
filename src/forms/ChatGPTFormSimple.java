package forms;

import javax.swing.*;
import java.awt.*;

public class ChatGPTFormSimple extends JFrame{
    private JPanel contentPaneSimple;
    private JLabel labelQuestion;
    private JTextField textQuestion;
    private JTextField textAnswer;
    private JButton buttonAsk;
    private JButton buttonClean;

    public ChatGPTFormSimple() {
        // Call method to create UI components
        createUIComponents();
    }

    private void createUIComponents() {
        // Initialize panel and components
        contentPaneSimple = new JPanel();
        contentPaneSimple.setLayout(new GridLayout(0, 2)); // Example layout manager

        // Initialize other components
        labelQuestion = new JLabel("Ask a question:");
        textQuestion = new JTextField(20);
        textAnswer = new JTextField(20);
        buttonAsk = new JButton("Ask");
        buttonClean = new JButton("Clean");

        // Add components to contentPane
        contentPaneSimple.add(labelQuestion);
        contentPaneSimple.add(textQuestion);
        contentPaneSimple.add(new JLabel("Answer:")); // Optional: Add label for answer
        contentPaneSimple.add(textAnswer);
        contentPaneSimple.add(buttonAsk);
        contentPaneSimple.add(buttonClean);

        // Set up the frame
        setTitle("ChatGPT client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPaneSimple);
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatGPTFormSimple form = new ChatGPTFormSimple();
            form.setVisible(true);
        });
    }
}