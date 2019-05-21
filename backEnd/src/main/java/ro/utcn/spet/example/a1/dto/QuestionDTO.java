package ro.utcn.spet.example.a1.dto;

import lombok.Data;
import ro.utcn.spet.example.a1.entity.Question;

import java.util.Date;
import java.util.List;

@Data

public class QuestionDTO {

    private Integer id;
    private String user;
    private String title;
    private String text;
    private List<String> tags;
    private Date creationTime;


    public static QuestionDTO ofEntity(Question question, String user, List<String> tags){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setUser(user);
        questionDTO.setTitle(question.getTitle());
        questionDTO.setText(question.getText());
        questionDTO.setTags(tags);
        questionDTO.setCreationTime(question.getCreationTime());

        return questionDTO;
    }





}

