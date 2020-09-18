package org.dantes.edmon.dto.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequestDTO {
    private String title;
    private List<String> genres;
    private Double minPrice;
    private Double maxPrice;
    private Double minRating;
    private Double maxRating;
}
