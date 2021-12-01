package pers.brian.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.brian.mall.modules.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-12-01 11:10
 * @Version: 0.0.1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "首页类型导航栏和banner组合数据传输对象", description = "首页类型导航栏和banner数据")
public class HomeMenusBannerDTO {

    private List<HomeMenusDTO> homeMenusList;
    private List<SmsHomeAdvertise> homeAdvertisesList;
}
