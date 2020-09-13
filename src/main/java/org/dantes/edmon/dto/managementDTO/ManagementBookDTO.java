package org.dantes.edmon.dto.managementDTO;

import lombok.Data;
import org.dantes.edmon.model.Author;

import java.util.List;

@Data
public class ManagementBookDTO {
    private String title;
    private Double price;
    private List<String> genres;
    private String imageLink;
    private List<Author> authors;
    private String bookDescription;
}
