package tbd.emergenciapp.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AppUserDTO {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String role;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
