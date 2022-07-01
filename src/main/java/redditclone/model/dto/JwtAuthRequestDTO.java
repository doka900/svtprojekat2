package redditclone.model.dto;

public class JwtAuthRequestDTO {
    private String username;
    private String password;

    public JwtAuthRequestDTO() {
        super();
    }

    public JwtAuthRequestDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
