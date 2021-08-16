package com.github.tcwloy.auth.service;

import com.github.tcwloy.auth.model.pojo.UserAuthorityInfo;

/**
 * 用户授权缓存服务
 */
public interface IUserAuthCache {
    /**
     * 获取token对应用户授权信息
     * @param token
     * @return
     */
    UserAuthorityInfo getUser(String token);
}
