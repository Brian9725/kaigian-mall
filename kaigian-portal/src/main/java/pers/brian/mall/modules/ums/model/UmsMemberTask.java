package pers.brian.mall.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 会员任务表
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_task")
@ApiModel(value="UmsMemberTask对象", description="会员任务表")
public class UmsMemberTask implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "赠送成长值")
    private Integer growth;

    @ApiModelProperty(value = "赠送积分")
    private Integer integration;

    @ApiModelProperty(value = "任务类型：0->新手任务；1->日常任务")
    private Integer type;


}
