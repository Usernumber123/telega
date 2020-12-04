import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class Friends {
    @Id
    public String id;
    public String username;
    public String friends;
    public boolean add;
    public Friends(String username, String friends) {
        this.username = username;
        this.friends = friends;
    }

    public Friends( String username, String friends, boolean add) {

        this.username = username;
        this.friends = friends;
        this.add = add;
    }

    public boolean isAdd() {
        return add;
    }

    public String getUsername() {
        return username;
    }

    public String getFriends() {
        return friends;
    }

    public Friends() {
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}
