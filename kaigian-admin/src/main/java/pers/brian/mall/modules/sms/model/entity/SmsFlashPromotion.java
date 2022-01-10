package pers.brian.mall.modules.sms.model.entity;

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
 * 限时购表
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_flash_promotion")
@ApiModel(value = "SmsFlashPromotion对象", description = "限时购表")
public class SmsFlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "上下线状态")
    private Integer status;

    @ApiModelProperty(value = "秒杀时间段名称")
    private Date createTime;


}
