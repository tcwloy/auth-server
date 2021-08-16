package com.github.tcwloy.auth.model.pojo;

import lombok.Data;

import java.util.List;

/**
 * 用户权限模型
 */
@Data
public class UserAuthorityInfo {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id集合
     */
    private List<Long> roleIds;
    /**
     * 权限code集合
     */
    private List<String> authorities;
}
