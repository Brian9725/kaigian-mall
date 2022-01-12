package pers.brian.mall.modules.sms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 人气推荐商品表
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_home_recommend_product")
@ApiModel(value="SmsHomeRecommendProduct对象", description="人气推荐商品表")
public class SmsHomeRecommendProduct implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;


}
