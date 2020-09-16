package org.dantes.edmon.dto;

import lombok.Data;

@Data
public class EvaluateReviewDTO {
    private String userEmail;
    private Integer reviewID;
    private Integer evaluation;
}
