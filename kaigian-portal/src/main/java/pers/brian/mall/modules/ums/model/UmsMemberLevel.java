package pers.brian.mall.modules.ums.model;

import java.math.BigDecimal;
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
 * 会员等级表
 * </p>
 *
 * @author BrianHu
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_level")
@ApiModel(value="UmsMemberLevel对象", description="会员等级表")
public class UmsMemberLevel implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer growthPoint;

    @ApiModelProperty(value = "是否为默认等级：0->不是；1->是")
    private Integer defaultStatus;

    @ApiModelProperty(value = "免运费标准")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty(value = "每次评价获取的成长值")
    private Integer commentGrowthPoint;

    @ApiModelProperty(value = "是否有免邮特权")
    private Integer privilegeFreeFreight;

    @ApiModelProperty(value = "是否有签到特权")
    private Integer privilegeSignIn;

    @ApiModelProperty(value = "是否有评论获奖励特权")
    private Integer privilegeComment;

    @ApiModelProperty(value = "是否有专享活动特权")
    private Integer privilegePromotion;

    @ApiModelProperty(value = "是否有会员价格特权")
    private Integer privilegeMemberPrice;

    @ApiModelProperty(value = "是否有生日特权")
    private Integer privilegeBirthday;

    private String note;


}
