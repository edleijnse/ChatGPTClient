package forms;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChatGPTFormSimple extends JFrame {
    private JPanel contentPaneSimple;
    private JLabel labelQuestion;
    private JTextField textQuestion;
    private JTextArea textAnswer;
    private JButton buttonAsk;
    private JButton buttonClean;

    public ChatGPTFormSimple() {
        createUIComponents();
    }

    private void createUIComponents() {
        contentPaneSimple = new JPanel();
        contentPaneSimple.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Increased insets for padding

        labelQuestion = new JLabel("Ask a question:");
        textQuestion = new JTextField(40); // Doubled width
        textAnswer = new JTextArea(10, 40); // Doubled size
        textAnswer.setLineWrap(true);
        textAnswer.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textAnswer);

        buttonAsk = new JButton("Ask");
        buttonClean = new JButton("Clean");

        // Set preferred sizes for the buttons (4 times smaller)
        Dimension buttonSize = new Dimension(80, 52); // (4 times smaller)
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

        // Add buttons on the same line
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(buttonAsk);
        buttonPanel.add(buttonClean);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPaneSimple.add(buttonPanel, c);

        // Set up the frame
        setTitle("ChatGPT client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPaneSimple);
        setPreferredSize(new Dimension(800, 600)); // Set preferred size of the frame (doubled)
        pack();
        setLocationRelativeTo(null); // Center the window

        buttonAsk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillAnswer();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
            }
        });
    }

    private void fillAnswer() throws IOException {
        OpenAIClient aiClient = new OpenAIClient();
        String apiKey = aiClient.readApiKey();
        CloseableHttpClient client = aiClient.initOpenAIClient();

        String inputText = textQuestion.getText();
        String myAnswer = aiClient.getOpenAIResponseGpt4Mini(inputText, client, apiKey);

        textAnswer.setText(myAnswer);
    }

    private void cleanFields() {
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