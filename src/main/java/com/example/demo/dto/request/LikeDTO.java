package com.example.demo.dto.request;

import com.example.demo.model.Story;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    @NonNull
    @NotBlank
    private Story story;
}
