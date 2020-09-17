package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.BookRatingDTO;
import org.dantes.edmon.repository.BookRatingDtoRepository;
import org.dantes.edmon.service.BookRatingDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookRatingDtoServiceImpl implements BookRatingDtoService {

    private BookRatingDtoRepository bookRatingDtoRepository;

    @Autowired
    public BookRatingDtoServiceImpl(BookRatingDtoRepository bookRatingDtoRepository){
        this.bookRatingDtoRepository = bookRatingDtoRepository;
    }

    @Override
    public BookRatingDTO findBookRatingByBookIdAndUserEmail(Integer bookID, String userEmail) {

        BookRatingDTO bookRatingInDatabase = null;

        try {
            bookRatingInDatabase = bookRatingDtoRepository.findBookRatingByBookIdAndUserEmail(bookID, userEmail);
        }catch (Exception e){

        }

        return bookRatingInDatabase;
    }

    @Override
    public BookRatingDTO saveBookRating(BookRatingDTO bookRatingDTO) {
        return bookRatingDtoRepository.saveBookRating(bookRatingDTO);
    }
}
