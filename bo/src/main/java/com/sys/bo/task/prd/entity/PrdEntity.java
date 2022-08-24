package com.sys.bo.task.prd.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import com.sys.bo.common.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "tbl_prd")
public class PrdEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long prdNo;
    
    @Column(length = 20, nullable = false)
    private String prdNm;

    @Column(length = 20, nullable = false)
    private String prdCd;

    @Column(length = 20, nullable = false)
    private String prdSts;
    
    private String prdDesc;
    
    private Long norPrc;
    
    private Long salPrc;

}