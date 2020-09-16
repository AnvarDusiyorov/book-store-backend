package org.dantes.edmon.repository;

import org.dantes.edmon.dto.EvaluateReviewDTO;
import org.dantes.edmon.dto.ReviewDTO;

import java.util.List;

public interface ReviewRepository {
    ReviewDTO findReviewByBookIdAndUserEmail(Integer bookID, String userEmail);
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getAllReviewByBookID(Integer bookID);
    EvaluateReviewDTO findEvaluateReviewByReviewIDandUserEmail(Integer reviewID, String userEmail);
    EvaluateReviewDTO saveEvaluateReview(EvaluateReviewDTO evaluateReviewDTO);
}
