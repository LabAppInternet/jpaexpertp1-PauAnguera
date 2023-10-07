package cat.tecnocampus.fgcstations.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Friends {

    @Id
    private String username;

    @ElementCollection
    private Set<String> friends;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }
}
