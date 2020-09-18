package org.dantes.edmon.dto;

import lombok.Data;
import org.dantes.edmon.model.Author;

import java.util.List;

@Data
public class ShortViewBookDTO {
    private Integer bookID;

    private String title;
    private List<Author> authors;
    private Double rating;

    private List<String> genres;
    private Double price;
    private String bookDescription;
    private String imageLink;
}
