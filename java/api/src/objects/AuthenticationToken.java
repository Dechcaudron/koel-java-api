package objects;

public class AuthenticationToken {

    private final String encodedToken;

    public AuthenticationToken(String encodedToken) {
        this.encodedToken = encodedToken;
    }
}
