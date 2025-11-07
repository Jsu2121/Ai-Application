package cs3220.aiapplication.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class UserBean {
    private User user;
    private final List<Exchange> exchangeHistory  = new ArrayList<>();

    public boolean isLoggedIn() {
        return user != null;
    }

    public void login(User user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    public List<Exchange> getExchangeHistory(){
        return exchangeHistory;
    }

    public void addExchange(Exchange exchange){
        exchangeHistory.add(exchange);
    }
}
