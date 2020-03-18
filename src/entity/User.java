package entity;

public class User {
    String name;
    int id;
    String email;
    String tel;

    public User(String name, int id, String email, String tel) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.tel = tel;
    }

    public User (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
