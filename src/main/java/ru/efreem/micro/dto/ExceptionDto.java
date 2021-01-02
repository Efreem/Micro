package ru.efreem.micro.dto;

public class ExceptionDto {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ExceptionDto generateUserNotFoundException(String description) {
        ExceptionDto exception = new ExceptionDto();

        exception.setName("UserNotFoundException");
        exception.setDescription(description);

        return exception;
    }
}
