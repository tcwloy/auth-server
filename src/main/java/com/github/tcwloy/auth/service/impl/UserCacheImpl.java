package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.tcwloy.auth.model.pojo.UserAuthorityInfo;
import com.github.tcwloy.auth.service.IUserAuthCache;
import com.github.tcwloy.auth.service.IUserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 用户授权信息缓存
 */
@Slf4j
public class UserCacheImpl implements IUserAuthCache {
    private LoadingCache<String, Optional<UserAuthorityInfo>> userInfoCache;
    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.userInfoCache = CacheBuilder.newBuilder().maximumSize(10000L).expireAfterWrite(60, timeUnit)
                .build(new CacheLoader<String, Optional<UserAuthorityInfo>>() {
            public Optional<UserAuthorityInfo> load(String key) throws Exception {
                return UserCacheImpl.this.loadUser(key);
            }
        });
    }

    private Optional<UserAuthorityInfo> loadUser(String token) {
        // todo 加载用户信息
        Optional<UserAuthorityInfo> optional = Optional.empty();
        if (StringUtils.isBlank(token)){
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public UserAuthorityInfo getUser(String token) {
        try {
            return userInfoCache.get(token).orElse(null);
        } catch (Exception e) {
            log.error("auth::get user error", e);
            return null;
        }
    }

}
