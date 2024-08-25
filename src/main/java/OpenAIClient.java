import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenAIClient {

    private static String readApiKey() throws IOException {
        // Get the current working directory
        String currentDir = System.getProperty("user.dir");
        // Get the directory one level above the current working directory
        String parentDir = new File(currentDir).getParent();
        // Read the API key file
        byte[] keyBytes = Files.readAllBytes(Paths.get(parentDir, "api_key"));
        // Convert to string and remove the end of line characters
        return new String(keyBytes).trim();
    }

    private static CloseableHttpClient initOpenAIClient() throws IOException {
        return HttpClients.createDefault();
    }


    private static void getOpenAIResponseGpt4(String inputText, CloseableHttpClient client, String apiKey) throws IOException {
        String user = "language teacher English, Spanish and German";

        ObjectMapper mapper = new ObjectMapper();

        // Create the root node
        ObjectNode rootNode = mapper.createObjectNode();
        // Add the 'model' field
        rootNode.put("model", "gpt-4o");
        // Create the 'messages' array node
        ArrayNode messagesNode = rootNode.putArray("messages");
        // Create a message object
        ObjectNode message1 = mapper.createObjectNode();
        message1.put("role", "user");
        message1.put("content", inputText);

        // Add the message object to the 'messages' array
        messagesNode.add(message1);
        // Convert the rootNode to a JSON string
        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);


        HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
        request.setHeader("Authorization", "Bearer " + apiKey);
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(requestBody));

        try (CloseableHttpResponse response = client.execute(request)) {
            JsonNode responseData = mapper.readTree(response.getEntity().getContent());
            String completion = responseData.get("choices").get(0).get("message").get("content").asText();
            System.out.println("Answer: " + completion);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private static String  getOpenAIResponseGpt4Mini(String inputText, CloseableHttpClient client, String apiKey) throws IOException {
        String user = "language teacher English, Spanish and German";

        ObjectMapper mapper = new ObjectMapper();

        // Create the root node
        ObjectNode rootNode = mapper.createObjectNode();
        // Add the 'model' field
        rootNode.put("model", "gpt-4o-mini");
        // Create the 'messages' array node
        ArrayNode messagesNode = rootNode.putArray("messages");
        // Create a message object
        ObjectNode message1 = mapper.createObjectNode();
        message1.put("role", "user");
        message1.put("content", inputText);

        // Add the message object to the 'messages' array
        messagesNode.add(message1);
        // Convert the rootNode to a JSON string
        String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

        HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
        request.setHeader("Authorization", "Bearer " + apiKey);
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(requestBody));

        try (CloseableHttpResponse response = client.execute(request)) {
            JsonNode responseData = mapper.readTree(response.getEntity().getContent());
            String completion = responseData.get("choices").get(0).get("message").get("content").asText();
            return completion;
            // System.out.println("Answer: " + completion);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        try {
            String apiKey = readApiKey();
            CloseableHttpClient client = initOpenAIClient();

            // Example usage
            String inputText = "Best things to see in Paris, in categories";
            String inputText2 = "Best things to see in Paris";
            // getOpenAIResponseGpt4(inputText, client, apiKey);
            String response = getOpenAIResponseGpt4Mini(inputText, client, apiKey);
            System.out.println("Answer: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}