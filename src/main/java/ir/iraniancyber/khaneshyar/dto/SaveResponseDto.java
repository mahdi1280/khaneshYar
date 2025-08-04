package ir.iraniancyber.khaneshyar.dto;

import java.io.Serializable;

public class SaveResponseDto implements Serializable {

    private final Integer id;

    public SaveResponseDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
