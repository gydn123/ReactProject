package com.exciting.login.entity;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
=======
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exciting.entity.Mypoint;
import com.exciting.entity.Orders;
import com.fasterxml.jackson.annotation.JsonManagedReference;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
@Table(name="member", uniqueConstraints = {@UniqueConstraint(columnNames = "member_id")})
public class Member {
	
	@Id
	private String member_id;
	
	private String m_name;
	
	private String m_birth;
	
	private String m_pass;
	
	private String m_email;
	
	private String m_address;
	
	@Column(name = "regidate", columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP")
	private Date m_regidate;
	
	private String m_phone;
	
	private String m_gender;
	
//	private int m_admin;
	
	private String m_image;
	
	private String m_kakao_id;
	
	private String m_github_id;
	
//	private int m_point;
	
	private String token;
	
	private String roles;
    
=======
@Table(name="member")
public class Member {
	
	public Member(String memberId) {
		// TODO Auto-generated constructor stub
	}

	
	@Id
    @Column(name = "member_id", length = 45)
    private String member_id;

    @Column(name = "m_name", length = 45)
    private String m_name;

    @Column(name = "m_birth", length = 45)
    private String m_birth;

    @Column(name = "m_pass", length = 100)
    private String m_pass;

    @Column(name = "m_email", length = 45)
    private String m_email;

    @Column(name = "m_address", length = 100)
    private String m_address;

    @Column(name = "m_regidate")
    private Date m_regidate;

    @Column(name = "m_phone", length = 45)
    private String m_phone;

    @Column(name = "m_gender", length = 45)
    private String m_gender;

    @Column(name = "m_image", length = 100)
    private String m_image;

    @Column(name = "m_kakao_id", length = 20)
    private String m_kakao_id;
    
    @Column(name = "m_github_id", length = 20)
    private String m_github_id;

    @Column(name = "roles", length = 100)
    private String roles;

    @Column(name = "token", length = 1000)
    private String token;
    
    
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Mypoint> mypoints;
    
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Orders> orders;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

}
