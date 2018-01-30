package validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

    @NotNull
    @NotEmpty(message = "Please enter your login!")
    @Size(min = 4, message = "Login to short!")
    private String login;

    @NotNull
    @NotEmpty(message = "Please enter your password.")
    @Size(min = 5, max = 20, message = "Your password must between 5 and 20 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
