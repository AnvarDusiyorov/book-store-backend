package org.dantes.edmon.controller;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.service.BookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping(path="/book")
public class BookPageController {

    private BookDtoService bookDtoService;

    @Autowired
    public BookPageController(BookDtoService bookDtoService){
        this.bookDtoService = bookDtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") int bookID, Principal principal){

        BookDTO bookDTO = bookDtoService.findBookByBookID(bookID);

        if(bookDTO == null){
            return new ResponseEntity<>(bookDTO, HttpStatus.NOT_FOUND);
        }


        String userEmail = null;
        try {
            userEmail = principal.getName();
        }catch (Exception e){ }

        if(userEmail != null){
            bookDTO.setRatingGivenByUser(bookDtoService.getRatingGivenByUser(userEmail));
        }

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
