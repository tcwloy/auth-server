package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tcwloy.auth.entity.UserRoleRel;
import com.github.tcwloy.auth.mapper.UserRoleRelMapper;
import com.github.tcwloy.auth.service.IUserRoleRelService;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;

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
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {

    @Override
    public List<Long> listRoleIdsByUserId(Long userId) {
        // 只查询角色id，可以走覆盖索引
        List<UserRoleRel> list = this.list(Wrappers.<UserRoleRel>lambdaQuery().select(UserRoleRel::getRoleId).eq(UserRoleRel::getUserId, userId));
        return StreamEx.of(list).map(UserRoleRel::getRoleId).toList();
    }
}
