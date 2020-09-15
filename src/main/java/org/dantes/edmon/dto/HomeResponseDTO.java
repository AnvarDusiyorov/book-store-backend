package org.dantes.edmon.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeResponseDTO {
    private List<String> genres;
    private List<BestsellerBookDTO> bestsellers;
}
