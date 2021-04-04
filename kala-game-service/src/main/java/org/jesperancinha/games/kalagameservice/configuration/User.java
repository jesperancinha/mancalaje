package org.jesperancinha.games.kalagameservice.configuration;


import org.springframework.lang.Nullable;


import java.sql.Timestamp;

public class User {

    private String email;

    private String name;

    private String password;

    private String role;

    @Nullable
    private Timestamp date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Nullable
    public Timestamp getDate() {
        return date;
    }

    public void setDate(
            @Nullable
                    Timestamp date) {
        this.date = date;
    }
}
