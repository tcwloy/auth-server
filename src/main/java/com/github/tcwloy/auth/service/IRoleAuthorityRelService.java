package com.github.tcwloy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tcwloy.auth.entity.RoleAuthorityRel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
public interface IRoleAuthorityRelService extends IService<RoleAuthorityRel> {
    /**
     * 根据角色id列表获取关联权限id列表
     * @param roleIds 角色id列表
     * @return 权限id集合
     */
    List<Long> listAuthorityIdsByRoleIds(List<Long> roleIds);
}
