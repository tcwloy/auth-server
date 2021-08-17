package com.github.tcwloy.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tcwloy.auth.entity.User;
import com.github.tcwloy.auth.mapper.UserMapper;
import com.github.tcwloy.auth.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息表 服务实现类
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
