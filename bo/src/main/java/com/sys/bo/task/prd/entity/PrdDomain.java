package com.sys.bo.task.prd.entity;


import org.apache.ibatis.type.Alias;

import com.sys.bo.common.entity.BaseDomain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Alias("prd")
public class PrdDomain extends BaseDomain{
    private Long prdNo;
    
    private String prdNm;

    private String prdCd;

    private String prdSts;
    
    private String prdDesc;
    
    private Long norPrc;
    
    private Long salPrc;

}