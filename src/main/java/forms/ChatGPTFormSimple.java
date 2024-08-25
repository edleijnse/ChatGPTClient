package forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGPTFormSimple extends JFrame {
    private JPanel contentPaneSimple;
    private JLabel labelQuestion;
    private JTextField textQuestion;
    private JTextArea textAnswer;
    private JButton buttonAsk;
    private JButton buttonClean;

    public ChatGPTFormSimple() {
        // Call method to create UI components
        createUIComponents();
    }

    private void createUIComponents() {
        // Initialize panel and components
        contentPaneSimple = new JPanel();
        contentPaneSimple.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Initialize other components
        labelQuestion = new JLabel("Ask a question:");
        textQuestion = new JTextField(20);
        textAnswer = new JTextArea(5, 20);
        textAnswer.setLineWrap(true);
        textAnswer.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textAnswer);

        buttonAsk = new JButton("Ask");
        buttonClean = new JButton("Clean");

        // Set preferred sizes for the buttons
        Dimension buttonSize = new Dimension(80, 50); // Width, Height
        buttonAsk.setPreferredSize(buttonSize);
        buttonClean.setPreferredSize(buttonSize);

        // Add components to contentPane
        c.gridx = 0;
        c.gridy = 0;
        contentPaneSimple.add(labelQuestion, c);

        c.gridx = 1;
        c.gridy = 0;
        contentPaneSimple.add(textQuestion, c);

        c.gridx = 0;
        c.gridy = 1;
        contentPaneSimple.add(new JLabel("Answer:"), c); // Optional: Add label for answer

        c.gridx = 1;
        c.gridy = 1;
        contentPaneSimple.add(scrollPane, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPaneSimple.add(buttonAsk, c);

        c.gridy = 3;
        contentPaneSimple.add(buttonClean, c);

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