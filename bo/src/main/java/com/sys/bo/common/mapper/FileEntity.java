package com.sys.bo.common.mapper;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity(name="tbl_file_attach")
public class FileEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long fileAttachId;

    @Column(length = 50, nullable = false)
    private String fileOrgNm;
    
    @Column(length = 50, nullable = false)
    private String fileSaveNm;

    @Column(length = 10, nullable = false)
    private String fileExt;
    
    @Column(length = 100, nullable = false)
    private String filePath;
    
    @Column(length = 1, nullable = false)
    @ColumnDefault("'Y'") 
    private String useYn = "Y";

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDate;
    
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updDate;

}
