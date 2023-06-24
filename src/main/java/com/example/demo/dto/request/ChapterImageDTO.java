package com.example.demo.dto.request;

import com.example.demo.model.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterImageDTO {
    @Lob
    @NonNull
    @NotBlank
    private String image;
    @NonNull
    @NotBlank
    private Chapter chapter;
}
