package befly.consult.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorryAnalyze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long worryAnalyzeId; // worry_analyze_id (PK, Auto-Increment)

    @Column(nullable = false)
    private Long worryId; // worry_id (FK, NOT NULL)

    @Column(nullable = false)
    private Integer preview1; // preview_1 (NOT NULL)

    @Column(nullable = false)
    private Integer preview2; // preview_2 (NOT NULL)

    @Column(nullable = false)
    private Integer preview3; // preview_3 (NOT NULL)

    @Column(nullable = false)
    private Integer preview4; // preview_4 (NOT NULL)

    @Column(nullable = false)
    private Integer preview5; // preview_5 (NOT NULL)

    @Column(nullable = false)
    private Integer preview6; // preview_6 (NOT NULL)

    @Column()
    private Integer after1; // after_1

    @Column()
    private Integer after2; // after_2

    @Column()
    private Integer after3; // after_3

    @Column()
    private Integer after4; // after_4

    @Column()
    private Integer after5; // after_5

    @Column()
    private Integer after6; // after_6

    @Column(length = 255)
    private String worryAnalyzeTitle; // worry_analyze_title

    @Lob
    private String description; // description (TEXT)
}
