package org.dantes.edmon.controller;

import org.dantes.edmon.dto.*;
import org.dantes.edmon.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path="/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<PileReviewDTO> getAllReviewByBookID(@PathVariable("id") int bookID){
        PileReviewDTO pileReviewDTO = new PileReviewDTO();
        pileReviewDTO.setReviews(reviewService.getAllReviewsByBookID(bookID));

        return new ResponseEntity<>(pileReviewDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResponseDTO> saveReview(@RequestBody ReviewDTO reviewDTO, Principal principal){
        String userEmail = principal.getName();
        reviewDTO.setUserEmail(userEmail);

        ReviewDTO reviewInDatabase = reviewService.findReviewByBookIdAndUserEmail(reviewDTO.getBookID(), reviewDTO.getUserEmail());

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();

        if(reviewInDatabase == null){
            reviewService.saveReview(reviewDTO);

            commonResponseDTO.setCreated("true");
            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CREATED);
        }else {
            commonResponseDTO.setCreated("false");
            commonResponseDTO.setReason("User already added review to this book!");
            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/evaluate")
    public ResponseEntity<CommonResponseDTO> saveEvaluateReview(@RequestBody EvaluateReviewDTO evaluateReviewDTO, Principal principal){
        String userEmail = principal.getName();
        evaluateReviewDTO.setUserEmail(userEmail);

        EvaluateReviewDTO evaluateReviewInDatabase = reviewService
                .findEvaluateReviewByReviewIDandUserEmail(
                    evaluateReviewDTO.getReviewID(),
                    evaluateReviewDTO.getUserEmail()
        );

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();

        if(evaluateReviewInDatabase == null){
            reviewService.saveEvaluateReview(evaluateReviewDTO);
            commonResponseDTO.setCreated("true");
            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CREATED);
        }else{
            commonResponseDTO.setCreated("false");
            commonResponseDTO.setReason("User already evaluated that review!");

            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CONFLICT);
        }

    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteReview(@RequestBody ){
//
//    }

}
