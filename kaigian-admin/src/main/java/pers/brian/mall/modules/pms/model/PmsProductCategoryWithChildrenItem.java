package pers.brian.mall.modules.pms.model;

import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-16 17:58
 * @Version: 0.0.1
 **/
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory{
	private List<PmsProductCategory> children;

	public List<PmsProductCategory> getChildren() {
		return children;
	}

	public void setChildren(List<PmsProductCategory> children) {
		this.children = children;
	}
}
