package org.dantes.edmon.dto;

import lombok.Data;
import org.dantes.edmon.model.Author;

import java.util.List;

@Data
public class BestsellerBookDTO {
    private String title;
    private List<Author> authors;
    private Double rating;
}
