package org.dantes.edmon.service;

import org.dantes.edmon.dto.BookRatingDTO;

public interface BookRatingDtoService {
    BookRatingDTO findBookRatingByBookIdAndUserEmail(Integer bookID, String userEmail);
    BookRatingDTO saveBookRating(BookRatingDTO bookRatingDTO);
}
