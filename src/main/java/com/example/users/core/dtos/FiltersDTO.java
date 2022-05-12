package com.example.users.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FiltersDTO {

    private int page;
    private int pageSize;
    private LocalDateTime minDate;
    private LocalDateTime maxDate;
}
