package com.sys.bo.task.system.domain;


import org.apache.ibatis.type.Alias;

import com.sys.bo.common.domain.BaseDomain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Alias("codeDtl")
public class CodeDetDomain extends BaseDomain{
    private Long cdDtltNo;
    private String cdDtlNm;
    private String cdDtlCd;

    private String cdDtlDesc;
    
    private String property01;
    
    private String property02;
    
    private String property03;
    
    private String property04;
    
    private String property05;
    
}