package pers.brian.mall.modules.sms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 优惠券和产品分类关系表
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_coupon_product_category_relation")
@ApiModel(value="SmsCouponProductCategoryRelation对象", description="优惠券和产品分类关系表")
public class SmsCouponProductCategoryRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long couponId;

    private Long productCategoryId;

    @ApiModelProperty(value = "产品分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "父分类名称")
    private String parentCategoryName;


}
