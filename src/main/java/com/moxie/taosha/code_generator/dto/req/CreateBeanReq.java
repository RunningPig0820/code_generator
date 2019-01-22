package com.moxie.taosha.code_generator.dto.req;

import com.moxie.taosha.code_generator.dto.bean.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @ClassName CreateBeanReq
 * @Description 请求参数
 * @Author zhangMin
 * @Date 2018/8/23 21:22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBeanReq {
    RouteInfo routeInfo;
    FileInfo fileInfo;
    List<Attribute> attrList;
}
