package com.sys.bo.task.member.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sys.bo.common.entity.BaseDomain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Getter
@Table(name = "tbl_members")
public class MemberEntity extends BaseDomain{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "MEMBERS_SEQ_GENERATOR")
    @SequenceGenerator(name = "MEMBERS_SEQ_GENERATOR", sequenceName = "MEMBERS_SEQ", initialValue = 1, allocationSize = 1)
    private Long memberNo;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String memberNm;
    
    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "memberNo", fetch = FetchType.EAGER)
    List<MemberRoleEntity> roles;

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", email='" + email + '\'' +
                '}';
    }

    @Builder
    public MemberEntity(Long memberNo, String email, String password, String memberNm) {
        this.memberNo = memberNo;
        this.email = email;
        this.password = password;
        this.memberNm = memberNm;
    }
}