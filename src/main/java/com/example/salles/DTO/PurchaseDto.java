package com.example.salles.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PurchaseDto {
    private List<ProductIdsDto> products;
}
