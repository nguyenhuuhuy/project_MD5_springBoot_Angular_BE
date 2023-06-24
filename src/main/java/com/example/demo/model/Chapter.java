package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chapter")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotBlank
    @Column(nullable = false,length = 30)
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    private Long chapterView = 0L;
    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();
    @OneToMany
    @JoinColumn(name = "chapter_id")
    List<ChapterImage> chapterImageList = new ArrayList<>();

    public Chapter(@NonNull String name, Story story) {
        this.name = name;
        this.story = story;
    }
}
