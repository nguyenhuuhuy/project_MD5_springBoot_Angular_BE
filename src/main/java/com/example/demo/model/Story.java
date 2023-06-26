package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "story")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // ten

    @Lob
    private String image;           // anh
//    @Column(columnDefinition = "bit default(0)")
    private Boolean status = false;

    private String content;                 // mieu ta

    @ManyToOne
    private Author author;

    @ManyToOne
    private User creator;
//    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "story_category",
            joinColumns = @JoinColumn(name = "story_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "story_id")
    private List<Chapter> chapterList = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "story_id")
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "story_id")
    private List<Like> likeList = new ArrayList<>();
    private Long totalView = 0L;

    public Story( String name,  String image, String content,  Author author,List<Category> categoryList) {
        this.name = name;
        this.image = image;
        this.content = content;
        this.author = author;
        this.categoryList = categoryList;
    }
}
