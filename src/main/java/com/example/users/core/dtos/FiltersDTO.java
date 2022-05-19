package com.example.users.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FiltersDTO {

    private int page;
    private int pageSize;
}
