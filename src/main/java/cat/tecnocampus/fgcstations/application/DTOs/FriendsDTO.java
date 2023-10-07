package cat.tecnocampus.fgcstations.application.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public class FriendsDTO {
    @Pattern(regexp = "^[a-z]*")
    @Size(min=3, max=255)
    private String username;

    @NotEmpty
    private List<String> friends;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFriends() {
        return (Set<String>) friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
