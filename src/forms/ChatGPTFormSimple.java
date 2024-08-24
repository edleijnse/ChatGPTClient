package forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGPTFormSimple extends JFrame {
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

        // Add action listeners
        buttonAsk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillAnswer();
            }
        });

        buttonClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
            }
        });
    }

    private void fillAnswer() {
        // Simply reflect the question in the answer field for demonstration
        textAnswer.setText(textQuestion.getText());
    }

    private void cleanFields() {
        // Clear the text fields
        textQuestion.setText("");
        textAnswer.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatGPTFormSimple form = new ChatGPTFormSimple();
            form.setVisible(true);
        });
    }
}