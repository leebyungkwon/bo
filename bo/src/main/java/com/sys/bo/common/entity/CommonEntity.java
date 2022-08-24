package com.sys.bo.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Alias("excelDtl")
@Table(name = "tbl_excel_dtl")
public class CommonEntity {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long excelDtlId;
    
    private Long columnNo;

    @Column(length =10, nullable = true)
    private String columnRow;
}
