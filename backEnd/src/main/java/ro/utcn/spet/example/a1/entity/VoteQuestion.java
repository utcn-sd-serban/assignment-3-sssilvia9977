
package ro.utcn.spet.example.a1.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.criteria.CriteriaBuilder;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VoteQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer usesr_id;
    private Integer questionId;
    private int value;

    public VoteQuestion(Integer userId, Integer questionId, int value){
        this.questionId = questionId;
        this.value = value;
        this.usesr_id = userId;
    }

}
