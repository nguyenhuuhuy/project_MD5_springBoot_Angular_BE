package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chapter_image")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChapterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String image;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    public ChapterImage(String image, Chapter chapter) {
        this.image = image;
        this.chapter = chapter;
    }
}
