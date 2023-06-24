package com.example.demo.dto.request;

import com.example.demo.model.ChapterImage;
import com.example.demo.model.Story;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @NotBlank
    private Story story;

}
