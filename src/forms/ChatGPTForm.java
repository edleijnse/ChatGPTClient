package forms;
import javax.swing.*;
public class ChatGPTForm extends JFrame{

    private JPanel contentPane;
    private JLabel labelQuestion;
    private JTextField textQuestion;
    private JButton buttonAskMeAnything;
    private JButton buttonClean;

    private void createUIComponents() {
        contentPane = new JPanel();
        // Initialize other components
        labelQuestion = new JLabel("Ask a question:");
        textQuestion = new JTextField(20);
        buttonAskMeAnything = new JButton("Ask");
        buttonClean = new JButton("Clean");
        // Add components to contentPane
        contentPane.add(labelQuestion);
        contentPane.add(textQuestion);
        contentPane.add(buttonAskMeAnything);
        contentPane.add(buttonClean);

        // TODO: place custom component creation code here
        setTitle("ChatGPT client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        pack();
    }
}
