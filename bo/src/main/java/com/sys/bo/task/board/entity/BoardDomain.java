package com.sys.bo.task.board.entity;


import org.apache.ibatis.type.Alias;

import com.sys.bo.common.domain.BaseDomain;
import com.sys.bo.util.excel.ExcelColumn;

import lombok.Data;

@Data
@Alias("board")
public class BoardDomain extends BaseDomain{
	
    private Long boardNo;
    private String boardType;
    
    @ExcelColumn(headerName = "타이틀", order = 0)
    private String boardTitle;

    @ExcelColumn(headerName = "내용", order = 1)
    private String boardCnts;

}