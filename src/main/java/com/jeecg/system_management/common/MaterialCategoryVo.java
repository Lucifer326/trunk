package com.jeecg.system_management.common;

/**
 * 物资类别管理vo
 *
 * @author 苑希阳
 */
public class MaterialCategoryVo {

    /**
     * 物资类别编号
     */
    private String categoryNumber;

    /**
     * 物资类别名称
     */
    private String categoryName;

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName.trim();
    }
}
