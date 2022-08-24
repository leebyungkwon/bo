package com.sys.bo.task.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sys.bo.common.entity.BaseDomain;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_member_role")
public class MemberRoleEntity extends BaseDomain{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ROLE_SEQ_GENERATOR")
	@SequenceGenerator(name = "MEMBER_ROLE_SEQ_GENERATOR", sequenceName = "MEMBER_ROLE_SEQ", initialValue = 1, allocationSize = 1)
    private Long roleNo;

    private String roleName;
    
    @ManyToOne
    @JoinColumn(name = "memberNo")
    @JsonIgnore
    MemberEntity memberNo;

}
