package com.github.tcwloy.auth.service.impl;

import com.github.tcwloy.auth.entity.Role;
import com.github.tcwloy.auth.mapper.RoleMapper;
import com.github.tcwloy.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
