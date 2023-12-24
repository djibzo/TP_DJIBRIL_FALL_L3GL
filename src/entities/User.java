package entities;

public class User {
    private int id;
    private String email, password, passwordHashed;
    private Role role;

    public User() {
    }

    public User(int id, String email, String password, String passwordHashed, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordHashed = passwordHashed;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return dechiffrer(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHashed() {

        return chiffrer(passwordHashed);
    }

    public void setPasswordHashed(String password) {
        this.passwordHashed = password;
    }
    private String chiffrer(String texteClair) {
        StringBuilder texteChiffre = new StringBuilder();

        for (int i = 0; i < texteClair.length(); i++) {
            char caractere = texteClair.charAt(i);

            // Remplacer chaque caractère par son suivant
            char caractereChiffre = (char) (caractere + 1);

            texteChiffre.append(caractereChiffre);
        }

        return texteChiffre.toString();
    }
    private   String dechiffrer(String texteChiffre) {
        StringBuilder texteClair = new StringBuilder();

        for (int i = 0; i < texteChiffre.length(); i++) {
            char caractere = texteChiffre.charAt(i);

            // Remplacer chaque caractère par son précédent
            char caractereClair = (char) (caractere - 1);

            texteClair.append(caractereClair);
        }

        return texteClair.toString();
    }
}
