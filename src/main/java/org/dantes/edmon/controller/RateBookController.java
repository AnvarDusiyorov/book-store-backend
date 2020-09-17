package org.dantes.edmon.controller;

import org.dantes.edmon.dto.BookRatingDTO;
import org.dantes.edmon.dto.CommonResponseDTO;
import org.dantes.edmon.service.BookRatingDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path="/ratebook")
public class RateBookController {

    private BookRatingDtoService bookRatingDtoService;

    public RateBookController(BookRatingDtoService bookRatingDtoService){
        this.bookRatingDtoService = bookRatingDtoService;
    }

    @PostMapping
    public ResponseEntity<CommonResponseDTO> saveBookRating(@RequestBody BookRatingDTO bookRatingDTO, Principal principal){
        String userEmail = principal.getName();

        bookRatingDTO.setUserEmail(userEmail);

        BookRatingDTO bookRatingInDatabase = bookRatingDtoService
                .findBookRatingByBookIdAndUserEmail(bookRatingDTO.getBookID(), bookRatingDTO.getUserEmail());

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();

        if(bookRatingInDatabase == null){
            commonResponseDTO.setCreated(true);
            bookRatingDtoService.saveBookRating(bookRatingDTO);
            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CREATED);
        }else{
            commonResponseDTO.setCreated(false);
            return new ResponseEntity<>(commonResponseDTO, HttpStatus.CONFLICT);
        }
    }
}
