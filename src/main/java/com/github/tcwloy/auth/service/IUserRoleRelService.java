package com.github.tcwloy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tcwloy.auth.entity.UserRoleRel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
public interface IUserRoleRelService extends IService<UserRoleRel> {
    /**
     * 根据用户id查询角色id列表
     * @param userId
     * @return 角色id列表
     */
    List<Long> listRoleIdsByUserId(Long userId);
}
