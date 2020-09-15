package org.dantes.edmon.repository;

import org.dantes.edmon.dto.BestsellerBookDTO;
import org.dantes.edmon.dto.HomeResponseDTO;

import java.util.List;

public interface HomeResponseDtoRepository {
    List<BestsellerBookDTO> getAllBestsellerBooks();
    List<String> getAllGenres();
}
