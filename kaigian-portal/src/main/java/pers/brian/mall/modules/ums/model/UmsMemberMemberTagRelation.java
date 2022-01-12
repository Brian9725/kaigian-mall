package pers.brian.mall.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户和标签关系表
 *
 * @author BrianHu
 * @create 2021-12-01 12:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_member_tag_relation")
@ApiModel(value="UmsMemberMemberTagRelation对象", description="用户和标签关系表")
public class UmsMemberMemberTagRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private Long tagId;


}
