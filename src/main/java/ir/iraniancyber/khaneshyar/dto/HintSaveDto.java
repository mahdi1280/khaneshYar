package ir.iraniancyber.khaneshyar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HintSaveDto {

    private final String title;
    private final Integer level;

    public HintSaveDto(String title, Integer level) {
        this.title = title;
        this.level = level;
    }

    @NotBlank(message = "hint.title.null")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "hint.level.null")
    public Integer getLevel() {
        return level;
    }
}
