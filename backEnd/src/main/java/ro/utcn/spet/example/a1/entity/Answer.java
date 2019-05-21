package ro.utcn.spet.example.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer questionId;
    private String text;
    private Date creationTime;

  public Answer( Integer userId, Integer questionId, String text){
      this.userId = userId;
      this.questionId = questionId;
      this.text = text;
      this.creationTime = new Date();

  }

}
