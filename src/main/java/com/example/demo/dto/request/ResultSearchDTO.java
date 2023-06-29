package com.example.demo.dto.request;

import com.example.demo.model.Author;
import com.example.demo.model.Category;
import com.example.demo.model.Story;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSearchDTO {
   private List<Author> authorList;
   private List<Category> categoryList;
   private List<Story> storyList;

}
