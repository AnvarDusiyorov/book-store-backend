package org.dantes.edmon.dto;

import lombok.Data;

import java.util.List;

@Data
public class PileReviewDTO {
    private List<ReviewDTO> reviews;
}
