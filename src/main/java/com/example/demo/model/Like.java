package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "likes"
//        ,uniqueConstraints = @UniqueConstraint(columnNames = {"story_id","user_id"})

)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story storyRelate;
    @Transient
    private Story story;
    private Date date = new Date();

    public Like(Story story) {
        this.story = story;
    }
}
