package com.sys.bo.task.system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sys.bo.common.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "cd_mst")
public class CodeMstEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cdMstNo;
    
    @Column(length = 20, nullable = false)
    private String cdMstNm;

    @Column(length = 20, nullable = false)
    private String cdMstCd;

    private String cdMstDesc;
    
}