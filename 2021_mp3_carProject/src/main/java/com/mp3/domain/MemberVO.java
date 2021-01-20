package com.mp3.domain;

import java.sql.Date;
//import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
    private Long member_no;
    private String member_id;
    private String member_pass;
	private String member_pass_user; 
    private String member_name;
    private String member_gender;
    private Date member_birth;
    private String member_phone;
    private String member_address;
    private String member_mail;
    private String member_mailaccept;
    private Date member_regDate;
    private Date member_updateDate;
    private String member_permission;
    private List<AuthVO> authList;
    private String send_mail;
    
}








