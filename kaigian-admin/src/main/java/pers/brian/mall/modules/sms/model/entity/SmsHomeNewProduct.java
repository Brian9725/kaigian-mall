package pers.brian.mall.modules.sms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 新鲜好物表
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_home_new_product")
@ApiModel(value="SmsHomeNewProduct对象", description="新鲜好物表")
public class SmsHomeNewProduct implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;


}
