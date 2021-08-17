package com.github.tcwloy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tcwloy.auth.entity.Authority;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
public interface IAuthorityService extends IService<Authority> {
    /**
     * 根据权限id列表获取code
     * @param ids id列表
     * @return code集合
     */
    List<String> listCodeByIds(List<Long> ids);
}
