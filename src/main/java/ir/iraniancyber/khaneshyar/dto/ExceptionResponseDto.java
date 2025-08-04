package ir.iraniancyber.khaneshyar.dto;

import java.io.Serializable;

public class ExceptionResponseDto implements Serializable {

    private final String title;
    private final String code;

    public ExceptionResponseDto(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
