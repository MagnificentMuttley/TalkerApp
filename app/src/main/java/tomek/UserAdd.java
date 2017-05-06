package tomek;

/**
 * Created by tomek on 06.05.2017.
 */

public class UserAdd extends User {
    private String password;

    public UserAdd(String email, String password, String userName) {

        super(email, userName);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
