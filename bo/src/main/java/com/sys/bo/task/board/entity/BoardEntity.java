package com.sys.bo.task.board.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sys.bo.common.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "tbl_board")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long boardNo;
    
    @Column(length = 20, nullable = false)
    private String boardType;

    @Column(length = 20, nullable = false)
    private String boardTitle;
    
    @Column(length = 400, nullable = false)
    private String boardCnts;

    private Long attchNo1;
    
    private Long attchNo2;
    
    private Long attchNo3;
    
    @Builder
    public BoardEntity(Long boardNo, String boardType, String boardTitle, String boardCnts) {
        this.boardNo = boardNo;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardCnts = boardCnts;
    }

}