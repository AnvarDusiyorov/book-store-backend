package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.EvaluateReviewDTO;
import org.dantes.edmon.dto.ReviewDTO;
import org.dantes.edmon.repository.ReviewRepository;
import org.dantes.edmon.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {

        return reviewRepository.saveReview(reviewDTO);
    }

    @Override
    public ReviewDTO findReviewByBookIdAndUserEmail(Integer bookID, String userEmail) {

        ReviewDTO reviewToReturn = null;

        try{
            reviewToReturn = reviewRepository.findReviewByBookIdAndUserEmail(bookID, userEmail);
            //log.info("IN findReviewByBookIdAndUserEmail - review: {} found: {}", reviewToReturn);

        }catch (Exception e){
            //log.info("IN findReviewByBookIdAndUserEmail - review with userEmail = {} and bookID = {}", userEmail, bookID);
        }
        return reviewToReturn;
    }

    @Override
    public List<ReviewDTO> getAllReviewsByBookID(Integer bookID) {
        return reviewRepository.getAllReviewByBookID(bookID);
    }

    @Override
    public EvaluateReviewDTO findEvaluateReviewByReviewIDandUserEmail(Integer reviewID, String userEmail) {
        EvaluateReviewDTO evaluateReviewToReturn = null;

        try {
            evaluateReviewToReturn = reviewRepository.findEvaluateReviewByReviewIDandUserEmail(reviewID, userEmail);
        }catch (Exception e){

        }

        return evaluateReviewToReturn;
    }

    @Override
    public EvaluateReviewDTO saveEvaluateReview(EvaluateReviewDTO evaluateReviewDTO) {
        return reviewRepository.saveEvaluateReview(evaluateReviewDTO);
    }
}
