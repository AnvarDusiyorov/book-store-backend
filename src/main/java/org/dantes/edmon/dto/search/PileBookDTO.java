package org.dantes.edmon.dto.search;

import lombok.Data;
import org.dantes.edmon.dto.BookDTO;
import org.dantes.edmon.dto.ShortViewBookDTO;

import java.util.List;

@Data
public class PileBookDTO {
    private List<ShortViewBookDTO> books;
}
