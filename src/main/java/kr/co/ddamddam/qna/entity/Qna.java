package kr.co.ddamddam.qna.entity;

import kr.co.ddamddam.qnaReply.entity.QnaReply;
import kr.co.ddamddam.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Setter
@Getter
@ToString(exclude = {"qnaReply", "qnaHashtag", "user"})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_idx")
    private Long qnaIdx; // 식별번호

    @Column(name = "qna_title", nullable = false, length = 100)
    private String qnaTitle;

    @Column(name = "qna_content", nullable = false, length = 3000)
    private String qnaContent;

    @Column(name = "qna_writer", nullable = false, length = 10)
    private String qnaWriter;

    @CreationTimestamp // 데이터가 추가되는 시간을 값으로 설정합니다.
    @Column(name = "qna_date", nullable = false)
    private LocalDateTime qnaDate;

    @Column(name = "qna_view", nullable = false)
    @Builder.Default
    private int qnaView = 0;

    @Column(name = "qna_adoption", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QnaAdoption qnaAdoption = QnaAdoption.N; // 기본값: 채택되지 않은 상태인 N

    @OneToMany(mappedBy = "qna", fetch = FetchType.LAZY)
    private List<QnaReply> qnaReply;

    @OneToMany(mappedBy = "qna", fetch = FetchType.LAZY)
    private List<QnaHashtag> qnaHashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx") // FK
    private User user;

    /**
     * 조회수 상승 처리용 Setter
     * @param qnaView - 게시글의 기존 조회수 + 1
     */
    public void setQnaView(int qnaView) {
        this.qnaView = qnaView;
    }

    /**
     * 게시글 채택 완료 처리용 Setter
     * @param qnaAdoption - 게시글의 채택 완료 여부 (Y or N)
     */
    public void setQnaAdoption(QnaAdoption qnaAdoption) {
        this.qnaAdoption = qnaAdoption;
    }
}
