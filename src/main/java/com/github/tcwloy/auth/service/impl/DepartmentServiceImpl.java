package com.github.tcwloy.auth.service.impl;

import com.github.tcwloy.auth.entity.Department;
import com.github.tcwloy.auth.mapper.DepartmentMapper;
import com.github.tcwloy.auth.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
