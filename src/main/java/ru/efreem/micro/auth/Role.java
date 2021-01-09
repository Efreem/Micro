package ru.efreem.micro.auth;

public class Role {
    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Role toRole(String name) {
        Role result = new Role();

        result.setName(name);

        return result;
    }

    @Override
    public String toString() {
        return name;
    }

}
