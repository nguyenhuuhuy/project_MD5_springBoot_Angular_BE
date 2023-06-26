package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "story_id")
    private Story story;
    private String comment;
    @Column(columnDefinition = "bit default(0)")
    private Boolean status = false;
    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();

    public Comment(Story story, String comment) {
        this.story = story;
        this.comment = comment;
    }
}
