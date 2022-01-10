package pers.brian.mall.modules.pms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品审核记录
 *
 * @author BrianHu
 * @create 2021-11-15 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_verify_record")
@ApiModel(value = "PmsProductVerifyRecord对象", description = "商品审核记录")
public class PmsProductVerifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Date createTime;

    @ApiModelProperty(value = "审核人")
    private String verifyMan;

    private Integer status;

    @ApiModelProperty(value = "反馈详情")
    private String detail;


}
