package org.dantes.edmon.dto;

import lombok.Data;

@Data
public class BookRatingDTO {
    private String userEmail;
    private Integer bookID;
    private Integer rating;
}
