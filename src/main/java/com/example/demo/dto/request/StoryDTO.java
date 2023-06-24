package com.example.demo.dto.request;

import com.example.demo.model.Author;
import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryDTO {
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    private String image;
    private String content;
    @NonNull
    @NotBlank
    private Author author;
    @NonNull
    private List<Category> categoryList = new ArrayList<>();
}
