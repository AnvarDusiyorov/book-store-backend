package org.dantes.edmon.dto;

import lombok.Data;
import org.dantes.edmon.dto.managementDTO.ManagementBookDTO;
import org.dantes.edmon.model.Author;

import java.util.List;

@Data
public class BookDTO{
    private Integer bookID;
    private String title;
    private Double price;
    private String bookDescription;
    private String imageLink;

    private List<Author> authors;
    private List<String> genres;

    private List<ReviewDTO> reviews;
    private Double rating;

    private Integer ratingGivenByUser;
}
