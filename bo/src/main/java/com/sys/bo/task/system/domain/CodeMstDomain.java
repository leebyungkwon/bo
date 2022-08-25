package com.sys.bo.task.system.domain;


import org.apache.ibatis.type.Alias;

import com.sys.bo.common.domain.BaseDomain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Alias("codeMst")
public class CodeMstDomain extends BaseDomain{
    private long cdMstNo;
    
    private String cdMstNm;

    private String cdMstCd;

    private String cdMstDesc;
}