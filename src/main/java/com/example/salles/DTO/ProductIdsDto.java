package com.example.salles.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
public class ProductIdsDto {
    private List<UUID> id;
}
