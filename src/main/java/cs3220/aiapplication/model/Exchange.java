package cs3220.aiapplication.model;

public class Exchange {
    private String userMessage;
    private String aiResponse;

    public Exchange(String userMessage, String aiResponse){
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }
}
