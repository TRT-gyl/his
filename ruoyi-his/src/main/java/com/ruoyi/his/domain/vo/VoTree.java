package com.ruoyi.his.domain.vo;

import java.util.List;

/**
 * 封装树形视图，用于前端级联选择器
 *
 * @author WuShaoYan
 * @date 2022/9/5
 */
public class VoTree {
    private Long id;
    private String name;
    private List<VoTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VoTree> getChildren() {
        return children;
    }

    public void setChildren(List<VoTree> children) {
        this.children = children;
    }
}
