package com.liteiot.admin.modules.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 微信公众号表
 *
 * @version 2022-05-11 17:09:03
 */
@Data
@Table(name = "user_mp")
@NoArgsConstructor
public class UserMp implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private int userId;

    /**
     * 该公众号的微信号
     */
    @Column(name = "mp_id")
    private String mpId;

    /**
     * 用户在微信号的唯一标识
     */
    @Column(name = "open_id")
    private String openId;


    public UserMp(int userId, String mpId, String openId) {
        this.userId = userId;
        this.mpId = mpId;
        this.openId = openId;

    }

}
