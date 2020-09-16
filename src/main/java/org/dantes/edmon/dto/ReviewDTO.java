package org.dantes.edmon.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Integer reviewID;
    private String userEmail;
    private Integer bookID;
    private String description;
    private Date createdAt;
    private Integer amountOfPluses;
    private Integer amountOfMinuses;
}

