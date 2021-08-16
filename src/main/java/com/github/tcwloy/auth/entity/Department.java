package com.github.tcwloy.auth.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tcwloy
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 部门类型
     */
    private Integer type;

    /**
     * 上级部门id
     */
    private Long parentId;

    /**
     * 逻辑删除标志
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime creataTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
