package ru.yandex.praktikum.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;


    public CreateCourier(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourier(){

    }


    public  String getLogin() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static CreateCourier creater() {
        return new CreateCourier(RandomStringUtils.randomAlphabetic(8), "1234", "saske");
    }

    public static CreateCourier from(CreateCourier courier) {
        return new CreateCourier(courier.getLogin(), courier.getPassword(), courier.getFirstName());
    }

}