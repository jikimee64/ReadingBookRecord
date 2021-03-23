package org.zerock.board.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply")
@ToString(exclude = "board")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Reply(String text, String replyer, Board board){
        this.text = text;
        this.replyer = replyer;
        this.board = board;
    }

}
