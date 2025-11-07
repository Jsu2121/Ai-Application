package cs3220.aiapplication.Controller;

import cs3220.aiapplication.model.DataStore;
import cs3220.aiapplication.model.Exchange;
import cs3220.aiapplication.model.UserBean;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AiController {
    private final ChatClient chatClient;
    public final DataStore dataStore;
    public final UserBean userBean;
    private final List<Exchange> history = new ArrayList<>();

    public AiController(DataStore dataStore, UserBean userBean, ChatClient.Builder chatClientBuilder){
        this.dataStore = dataStore;
        this.userBean = userBean;
        this.chatClient = chatClientBuilder.build();
    }

    private String realChat(String message){
        List<Message> messages = new ArrayList<>();
        for(var exchange : history){
            messages.add(new UserMessage(exchange.getUserMessage()));
            messages.add(new AssistantMessage(exchange.getAiResponse()));
        }
        messages.add(new UserMessage(message));
        return chatClient.prompt(new Prompt(messages)).call().content();
    }



}
