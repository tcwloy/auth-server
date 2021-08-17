package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tcwloy.auth.entity.RoleAuthorityRel;
import com.github.tcwloy.auth.mapper.RoleAuthorityRelMapper;
import com.github.tcwloy.auth.service.IRoleAuthorityRelService;
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
public class RoleAuthorityRelServiceImpl extends ServiceImpl<RoleAuthorityRelMapper, RoleAuthorityRel> implements IRoleAuthorityRelService {

    @Override
    public List<Long> listAuthorityIdsByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.EMPTY_LIST;
        }
        // 可以走覆盖索引
        List<RoleAuthorityRel> list = this.list(Wrappers.<RoleAuthorityRel>lambdaQuery().select(RoleAuthorityRel::getAuthorityId).in(RoleAuthorityRel::getRoleId, roleIds));
        return StreamEx.of(list).map(RoleAuthorityRel::getAuthorityId).toList();
    }
}
