package org.example.models;


import java.util.Date;

public class User {
    private String name;
    private String email;
    private String password;
    private Date birthdate;

    public User(String name, String email, String password, Date birthdate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public String trocarSenha(String antigaSenha, String novaSenha) {
        if (!this.getPassword().equals(antigaSenha)) {
            return "Senha antiga inválida";
        } else {
            this.password = novaSenha;
            return "Senha alterada para: " + this.password;
        }
    }

    public String trocarEmail(String novoEmail) {
        this.email = novoEmail;
        return "Email alterado para: " + this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }
}

