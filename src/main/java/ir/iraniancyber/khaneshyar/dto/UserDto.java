package ir.iraniancyber.khaneshyar.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    //todo validate add *

    private final String username;
    private final String password;
    private final String nationalCode;
    private final String nationality;
    private final String name;

    public UserDto(String username, String password, String nationalCode, String nationality, String name) {
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
        this.nationality = nationality;
        this.name = name;
    }

    @NotBlank(message = "user.username.null")
    public String getUsername() {
        return username;
    }

    @NotBlank(message = "user.password.null")
    public String getPassword() {
        return password;
    }

    @NotBlank(message = "user.nationalCode.null")
    public String getNationalCode() {
        return nationalCode;
    }

    @NotBlank(message = "user.nationality.null")
    public String getNationality() {
        return nationality;
    }

    @NotBlank(message = "user.name.null")
    public String getName() {
        return name;
    }
}
