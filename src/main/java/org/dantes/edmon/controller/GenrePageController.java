package org.dantes.edmon.controller;

import org.dantes.edmon.dto.PileOfGenres;
import org.dantes.edmon.dto.search.PileBookDTO;
import org.dantes.edmon.service.BookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/genre")
public class GenrePageController {

    private BookDtoService bookDtoService;

    @Autowired
    public GenrePageController(BookDtoService bookDtoService){
        this.bookDtoService = bookDtoService;
    }

    @GetMapping("/{genreName}")
    public ResponseEntity<PileBookDTO> getAllBooksByGenre(@PathVariable String genreName){
        List<Integer> bookIdList = bookDtoService.getAllBookIdByGenre(genreName);

        PileBookDTO pileBookDTO = new PileBookDTO();
        if(bookIdList != null){
            pileBookDTO.setBooks(bookDtoService.getAllShortViewBooksByListOfBookID(bookIdList));
            return new ResponseEntity<>(pileBookDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(pileBookDTO, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<PileOfGenres> getAllGenres(){
        return new ResponseEntity<>(bookDtoService.getAllGenres(), HttpStatus.OK);
    }
}
