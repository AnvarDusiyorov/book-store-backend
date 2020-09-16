package org.dantes.edmon.service;

import org.dantes.edmon.dto.EvaluateReviewDTO;
import org.dantes.edmon.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    ReviewDTO findReviewByBookIdAndUserEmail(Integer bookID, String userEmail);
    List<ReviewDTO> getAllReviewsByBookID(Integer bookID);
    EvaluateReviewDTO findEvaluateReviewByReviewIDandUserEmail(Integer reviewID, String userEmail);
    EvaluateReviewDTO saveEvaluateReview(EvaluateReviewDTO evaluateReviewDTO);
}
