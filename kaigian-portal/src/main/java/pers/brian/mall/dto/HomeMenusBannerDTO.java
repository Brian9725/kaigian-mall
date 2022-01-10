package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * @author BrianHu
 * @create 2021-12-01 12:00 11:10
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "首页类型导航栏和banner组合数据传输对象", description = "首页类型导航栏和banner数据")
public class HomeMenusBannerDTO {

    private List<HomeMenusDTO> homeMenusList;
    private List<SmsHomeAdvertise> homeAdvertisesList;
}
