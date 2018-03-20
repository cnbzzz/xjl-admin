package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bzzz123
 * @since 2018-03-19
 */
@TableName("client_info")
public class ClientInfo extends Model<ClientInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String username;
    private String organization;
    private String email;
    private String phone;
    @TableField("computer_code")
    private String computerCode;
    @TableField("auth_code")
    private String authCode;
    @TableField("recom_username")
    private String recomUsername;
    @TableField("recom_phone")
    private String recomPhone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComputerCode() {
        return computerCode;
    }

    public void setComputerCode(String computerCode) {
        this.computerCode = computerCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRecomUsername() {
        return recomUsername;
    }

    public void setRecomUsername(String recomUsername) {
        this.recomUsername = recomUsername;
    }

    public String getRecomPhone() {
        return recomPhone;
    }

    public void setRecomPhone(String recomPhone) {
        this.recomPhone = recomPhone;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
        "id=" + id +
        ", username=" + username +
        ", organization=" + organization +
        ", email=" + email +
        ", phone=" + phone +
        ", computerCode=" + computerCode +
        ", recomUsername=" + recomUsername +
        ", recomPhone=" + recomPhone +
        "}";
    }
}
