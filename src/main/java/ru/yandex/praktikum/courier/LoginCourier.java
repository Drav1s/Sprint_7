package ru.yandex.praktikum.courier;

public class LoginCourier {

    private String login;
    private String password;

    public LoginCourier(String login, String password){
        this.login = login;
        this.password = password;
    }

    public LoginCourier () {

    }

    public static LoginCourier from(CreateCourier courier) {
        return new LoginCourier(courier.getLogin(), courier.getPassword());
    }

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
