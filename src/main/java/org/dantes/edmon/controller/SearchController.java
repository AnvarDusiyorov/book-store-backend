package org.dantes.edmon.controller;

import org.dantes.edmon.dto.search.PileBookDTO;
import org.dantes.edmon.dto.search.SearchRequestDTO;
import org.dantes.edmon.service.BookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/search")
public class SearchController {

    private BookDtoService bookDtoService;

    @Autowired
    public SearchController(BookDtoService bookDtoService){
        this.bookDtoService = bookDtoService;
    }


    @PostMapping
    public ResponseEntity<PileBookDTO> getAllSuitableBooks(@RequestBody SearchRequestDTO searchRequestDTO){
        return new ResponseEntity<>(bookDtoService.getAllShortViewBooksBySearchRequestDTO(searchRequestDTO), HttpStatus.OK);
    }

}
