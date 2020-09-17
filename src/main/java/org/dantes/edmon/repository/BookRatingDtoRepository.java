package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BookRatingDTO;

public interface BookRatingDtoRepository {
    BookRatingDTO findBookRatingByBookIdAndUserEmail(Integer bookID, String userEmail);
    BookRatingDTO saveBookRating(BookRatingDTO bookRatingDTO);
}
