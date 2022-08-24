package com.sys.bo.task.system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sys.bo.common.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "cd_det")
public class CodeDetEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cdDetNo;

    @Column(length = 20, nullable = false)
    private String cdDetNm;

    @Column(length = 20, nullable = false)
    private String cdDetCd;

    private String cdDetDesc;
    
    private String property01;
    
    private String property02;
    
    private String property03;
    
    private String property04;
    
    private String property05;
    
    @ManyToOne
    @JoinColumn(name = "cdMstNo") 
    private CodeMstEntity codeMst;
    
}