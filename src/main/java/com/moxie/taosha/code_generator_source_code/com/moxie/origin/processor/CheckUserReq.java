package com.moxie.taosha.code_generator_source_code.com.moxie.origin.processor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.moxie.orion.common.dto.platform.req.UserCheckReq;
import com.moxie.taosha.code_generator_annotation.annotations.Conversion;
import com.moxie.taosha.code_generator_annotation.enums.ConversionPattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @ClassName WanDaRequest
 * @Description 用户检测接口
 * @Author hangman
 * @Date 2018-12-24 10:31:41
 * @Version 1.0
 **/
@ApiModel
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Conversion(sourceClass = UserCheckReq.class)
@Component
public class CheckUserReq {

    @NotNull
    @ApiModelProperty("通过身份证号（遇到字母转为大写）MD5加密后的字符串")
    @Conversion(
            conversionProperty = "#String.idNumber",
            conversionPattern= ConversionPattern.CONVERSION_PATTERN,
            method = "method",
            methodArgs = {"method"}
    )
    private String idNumberMd5;

    @NotNull
    @ApiModelProperty("用户身份证号（遇到字母转为大写），如340881197901012345")
    @Conversion(conversionProperty = "#String.idcard")
    private String idNumber;

    @NotNull
    @ApiModelProperty("用户手机号，后4位掩码，如1391234****")
    @Conversion(conversionProperty = "#String.userMobile")
    private String phone;

    @NotNull
    @ApiModelProperty("用户姓名")
    @Conversion(conversionProperty = "#String.userName")
    private String userName;

}