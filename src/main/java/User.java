
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;

@Document
public class User {
    @Id
    public String id;
    public String username;
    public String password;
    public boolean online;

    public User() {}


    public User(String username,String password) {

        this.username = username;
        this.password = password;
    }
    public User(String username) {

        this.username = username;

    }
    public User(String username, String password, boolean online) {
        this.username = username;
        this.password = password;
        this.online = online;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }





}
