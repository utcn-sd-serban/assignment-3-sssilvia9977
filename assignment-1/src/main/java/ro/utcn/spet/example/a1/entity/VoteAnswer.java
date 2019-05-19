package ro.utcn.spet.example.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer usesr_id;
    private Integer answerId;
    private int value;

    public VoteAnswer(Integer usesr_id,Integer answerId , int value){
        this.usesr_id = usesr_id;
        this.value = value;
        this.answerId = answerId;
    }

}
