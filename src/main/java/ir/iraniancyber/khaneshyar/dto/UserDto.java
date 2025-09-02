package ir.iraniancyber.khaneshyar.dto;

public class UserDto {

    //todo validate add

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }
}
