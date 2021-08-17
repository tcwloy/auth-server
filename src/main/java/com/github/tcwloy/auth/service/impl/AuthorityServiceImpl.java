package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tcwloy.auth.entity.Authority;
import com.github.tcwloy.auth.mapper.AuthorityMapper;
import com.github.tcwloy.auth.service.IAuthorityService;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    @Override
    public List<String> listCodeByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }
        List<Authority> list = list(Wrappers.<Authority>lambdaQuery().select(Authority::getCode).in(Authority::getId));
        return StreamEx.of(list).map(Authority::getCode).toList();
    }
}
