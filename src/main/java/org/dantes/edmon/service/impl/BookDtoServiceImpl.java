package org.dantes.edmon.service.impl;

import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.repository.BookDtoRepository;
import org.dantes.edmon.service.BookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDtoServiceImpl implements BookDtoService {

    private BookDtoRepository bookDtoRepository;

    @Autowired
    public BookDtoServiceImpl(BookDtoRepository bookDtoRepository){
        this.bookDtoRepository = bookDtoRepository;
    }

    @Override
    public BookDTO findBookByBookID(Integer bookID) {

        BookDTO bookDTO = null;

        try {
            bookDTO = bookDtoRepository.findBookByBookID(bookID);
        }catch (Exception e){

        }

        return bookDTO;
    }

    @Override
    public Integer getRatingGivenByUser(String userEmail) {
        Integer userRating = null;

        try {
            userRating = bookDtoRepository.getRatingGivenByUser(userEmail);
        }catch (Exception e){ }

        return userRating;
    }

}
