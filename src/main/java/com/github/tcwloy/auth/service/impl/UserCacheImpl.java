package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.tcwloy.auth.model.pojo.TokenInfo;
import com.github.tcwloy.auth.model.pojo.UserAuthorityInfo;
import com.github.tcwloy.auth.service.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 用户授权信息缓存
 */
@Slf4j
public class UserCacheImpl implements IUserAuthCache {
    private static final int CACHE_MAX_SIZE = 100000;
    private static final int DURATION_SECONDS = 60;
    private LoadingCache<String, Optional<UserAuthorityInfo>> userInfoCache;
    @Autowired
    private IUserRoleRelService userRoleRelService;
    @Autowired
    private IRoleAuthorityRelService roleAuthorityRelService;
    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private ITokenService tokenService;

    @PostConstruct
    public void init() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.userInfoCache = CacheBuilder.newBuilder().maximumSize(CACHE_MAX_SIZE).expireAfterWrite(DURATION_SECONDS, timeUnit)
                .build(new CacheLoader<String, Optional<UserAuthorityInfo>>() {
            public Optional<UserAuthorityInfo> load(String key) throws Exception {
                return UserCacheImpl.this.loadUser(key);
            }
        });
    }

    private Optional<UserAuthorityInfo> loadUser(String token) {
        if (StringUtils.isBlank(token)){
            return Optional.empty();
        } else {
            TokenInfo tokenInfo = tokenService.getTokenInfo(token);
            UserAuthorityInfo.UserAuthorityInfoBuilder userAuthorityInfoBuilder = UserAuthorityInfo.builder();
            userAuthorityInfoBuilder.userId(tokenInfo.getUserId()).userName(tokenInfo.getUserName());
            //获取用户角色信息
            List<Long> roleIds = userRoleRelService.listRoleIdsByUserId(tokenInfo.getUserId());
            if (CollectionUtils.isNotEmpty(roleIds)) {
                userAuthorityInfoBuilder.roleIds(roleIds);
                //获取权限id列表
                List<Long> authorityIds = roleAuthorityRelService.listAuthorityIdsByRoleIds(roleIds);
                if (CollectionUtils.isNotEmpty(authorityIds)) {
                    //获取权限code信息
                    List<String> authorityCodes = authorityService.listCodeByIds(authorityIds);
                    userAuthorityInfoBuilder.authorities(authorityCodes);
                }
            }
            return Optional.of(userAuthorityInfoBuilder.build());
        }
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
