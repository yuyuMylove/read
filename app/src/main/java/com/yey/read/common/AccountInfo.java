package com.yey.read.common;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.yey.read.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * User: sunnie
 * Date: 15-06-02
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
@Table(name="accountInfo")
public class AccountInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6919461967497580385L;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}

	@Column(column="uid")
    private int uid; //注册成功返回的uid
    @Column(column="phone")
    private String phone; //注册成功绑定的手机号，目前只支持手机号注册
	@Column(column="password")
	private String password; //注册成功后的密码
    @Column(column = "nickname")
    private String nickname; //昵称，默认为手机号
    @Column(column="address")
    private String address; //地址，默认为空
	@Column(column="gender")
	private String gender; //性别
	@Column(column="birthday")
	private String birthday; //生日
	@Column(column="favorite")
	private String favorite; //兴趣爱好
	@Column(column="avatar")
	private String avatar; //头像
	@Column(column="vip")
	private int vip; //会员
	@Column(column="point")
	private int point; //积分
    @Column(column="phonecode")
    private String phonecode; //验证码

}
