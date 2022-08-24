package com.sys.bo.common.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class BaseDomain extends PagingDomain{

    private String useYn;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
}
