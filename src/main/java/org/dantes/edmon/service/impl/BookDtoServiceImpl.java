package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.PileOfGenres;
import org.dantes.edmon.dto.ShortViewBookDTO;
import org.dantes.edmon.dto.search.PileBookDTO;
import org.dantes.edmon.dto.search.SearchRequestDTO;
import org.dantes.edmon.repository.BookDtoRepository;
import org.dantes.edmon.service.BookDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    @Override
    public List<Integer> getAllBookIdByGenre(String genreName) {

        List<Integer> bookIdList = null;

        try {
            bookIdList = bookDtoRepository.getAllBookIdByGenre(genreName);
            if(bookIdList.isEmpty()){
                bookIdList = null;
            }
        }catch (Exception e){
        }

        return bookIdList;
    }

    @Override
    public ShortViewBookDTO findShortViewBookByBookID(Integer bookID) {
        ShortViewBookDTO shortViewBookDTO = null;

        try {
            shortViewBookDTO = bookDtoRepository.findShortViewBookByBookID(bookID);
        }catch (Exception e){

        }

        return shortViewBookDTO;
    }

    @Override
    public PileOfGenres getAllGenres() {
        PileOfGenres pileOfGenres = new PileOfGenres();
        pileOfGenres.setGenres(bookDtoRepository.getAllGenres());
        return pileOfGenres;
    }

    @Override
    public List<ShortViewBookDTO> getAllShortViewBooksByListOfBookID(List<Integer> bookIdList) {
        List<ShortViewBookDTO> books = new ArrayList<>();

        for (Integer bookID : bookIdList){
            books.add(findShortViewBookByBookID(bookID));
        }

        return books;
    }


    @Override
    public PileBookDTO getAllShortViewBooksBySearchRequestDTO(SearchRequestDTO searchRequestDTO) {
        PileBookDTO pileBookDTO = new PileBookDTO();
        pileBookDTO.setBooks(bookDtoRepository.getAllBooksBySearchRequestDTO(searchRequestDTO));
        return pileBookDTO;
    }

}
