package ${packageName};

import com.moxie.commons.BaseJsonUtils;
import com.moxie.orion.common.EnvironmentTag;
import com.moxie.orion.common.dto.BaseResponse;
import com.moxie.orion.common.dto.OrionOrderInfo;
import com.moxie.orion.common.dto.OrionUserMappingInfo;
import com.moxie.orion.common.processor.CommonProcessor;
import com.moxie.orion.common.service.DaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName AbstractCommonProcessor
 * @Description 公共类
 * @Author ${author}
 * @Date  ${date}
 * @Version 1.0
 **/
@Slf4j
public abstract class AbstractCommonProcessor<I, O> extends CommonProcessor {

    public AbstractCommonProcessor() {
        super.ORG_ID = "";
        super.PRODUCT_ID = "";

        String seviceTag = System.getProperty("service.tag");
        /**
         * 返利生产
         */
        if (StringUtils.isNotBlank(seviceTag)) {
            switch (seviceTag) {
                //返利生产
                case EnvironmentTag.FANLI_PROD:
                    super.ORG_ID = "";
                    super.PRODUCT_ID = "";
                    break;
                //返利测试
                case EnvironmentTag.FANLI_TEST:
                    super.ORG_ID = "";
                    super.PRODUCT_ID = "";
                    break;
                //流星云生产
                case EnvironmentTag.FLOW_PROD:
                    break;
                //流星云测试
                case EnvironmentTag.FLOW_TEST:
                    super.ORG_ID = "";
                    super.PRODUCT_ID = "";
                    break;
                default:break;
            }
        }

    }

    /**
     * 请求对接方数据
     * @param enums 日志定位
     * @param input 数据中心返回数据
     * @param url   对接方地址
     * @param <T>   数据中心 转 请求数据类型
     * @param args  补充属性
     * @return
     */
    protected<T> Object getResponseBody(Enum enums, Object input, String url,Class<T> clazz,String ... args){
        //拼接请求参数 Bean
        Object object = getRequestParam(enums, input);
        if(object instanceof BaseResponse){
            return object;
        }

        String reqDataJson = BaseJsonUtils.writeValue((T)object);
        ${product}Request request = initRequest(reqDataJson,args[0]);

        String reqStr = BaseJsonUtils.writeValue(input);
        String userId = OrionLogUtils.getUserIdByReq(reqStr);
        String orderNo = OrionLogUtils.getOrderNoByReq(reqStr);
        log.info("user_id={} order_no={} ${product} enums:{} {}",userId,orderNo,enums,BaseJsonUtils.writeValue(request));


        //发送post请求
        String body = restClient().postForEntity(url, request, String.class).getBody();
        return getResponse(enums, input, body);
    }



    /**
     * 获取 OrionOrderInfo对象
     * @param type 查找类型
     * @param args args[0]：对应的查找条件
     * @return
     * @throws NullPointerException
     */
    protected OrionOrderInfo getOrionOrderInfo(String type,String... args) throws NullPointerException{
        OrionOrderInfo orionOrderInfo = null;

        DaoService daoService = this.getDaoService();
        Objects.requireNonNull(daoService);

        switch (type){
            case "OrderNo":
                orionOrderInfo = daoService.getOrderInfoByOrderNo(args[0]);
                break;
            case "LoanNo":
                orionOrderInfo = daoService.getOrderInfoByLoanNo(args[0]);
                break;
            case "OrderMappingNo":
                orionOrderInfo = daoService.getOrderInfoByOrderMappingNo(args[0]);
            default:
                break;
        }
        Objects.requireNonNull(orionOrderInfo);

        return orionOrderInfo;
    }

        /**
        * 获取 List<OrionOrderInfo>对象
        * @param type 查找类型
        * @param productId 产品ID userMappingId 用户名称 status 数据状态
        * @return
        * @throws NullPointerException
        */
        public List<OrionOrderInfo> getOrionOrderInfoList(String type, String productId, String userMappingId, String status){
            List<OrionOrderInfo> orionOrderInfoList = null;

            DaoService daoService = this.getDaoService();
            Objects.requireNonNull(daoService);

            switch (type){
                case "MappingId":
                    orionOrderInfoList = daoService.getOrderInfoByMappingId(productId,userMappingId,status);
                    break;
                case "UserId":
                    orionOrderInfoList = daoService.getOrderInfoByUserId(productId,userMappingId,status);
                    break;
                default:
                    break;
            }

            Objects.requireNonNull(orionOrderInfoList);

            return orionOrderInfoList;
        }

    /**
    * 获取 OrionUserMappingInfo 对象
    * @param type 查询类型
    * @param productId 产品ID ,args[1]:查询条件
    * @return
    * @throws NullPointerException
    */
    public OrionUserMappingInfo getOrionUserMappingInfo(String type,String productId, String userId) throws NullPointerException{
        OrionUserMappingInfo orionOrderInfoList = null;

        DaoService daoService = this.getDaoService();
        Objects.requireNonNull(daoService);

        switch(type){
            case "MappingId":
                orionOrderInfoList = daoService.getUserMappingInfoByMappingId(productId,userId);
                break;
            case "UserId":
                orionOrderInfoList =  daoService.getUserMappingInfo(productId,userId);
                break;
            case "ApplyNo":
                orionOrderInfoList = daoService.getUserInfoByApplyNo(productId,userId);
                break;
            case "OrderNo" :
                OrionOrderInfo orionOrderInfo = getOrionOrderInfo("OrderNo",userId);
                Objects.requireNonNull(orionOrderInfo);
                orionOrderInfoList = daoService.getUserMappingInfo(productId,orionOrderInfo.getUserId());
                break;
            default:
                break;
        }
        Objects.requireNonNull(orionOrderInfoList);

        return orionOrderInfoList;
    }

    /**
     * 记录 OrionOrderInfo 到中间数据数据库
     * @param orionOrderInfo
     * @throws NullPointerException
     */
    public void setOrionOrderInfo(OrionOrderInfo orionOrderInfo) throws NullPointerException{
        DaoService daoService = this.getDaoService();
        Objects.requireNonNull(daoService);
        daoService.initOrUpdateOrder(orionOrderInfo);
    }

    /**
     * 记录 OrionUserMappingInfo 到中间数据数据库
     * @param orionOrderInfo
     * @throws NullPointerException
     */
    public void setOrionUserMappingInfo(OrionUserMappingInfo orionOrderInfo) throws NullPointerException{
        DaoService daoService = this.getDaoService();
        Objects.requireNonNull(daoService);
        daoService.initOrUpdateUserMappingInfo(orionOrderInfo);
    }

    /**
    *  加密数据转化为对应类
    */
    public <T> T getBean(String response, Class<T> clazz){
        ${product}Response ${product}Response = (${product}Response)BaseJsonUtils.readValue(response, ${product}Response.class);

        //TODO 需要自己补充
        String data = get${product}ResponseData();
        //解密失败
        if(StringUtils.isEmpty(data)){
            return null;
        }

        return BaseJsonUtils.readValue(data, clazz);
    }

    /**
    * 响应数据校验
    * @param response
    * @return
    */
    protected Object verificationResponse(String response,Enum requestStep){
        //${product}Response response = (${product}Response)BaseJsonUtils.readValue(response, response.class);
        //String code = response.getResultSuccess();
        //if(!"".equalsIgnoreCase(code)){
        //    return new BaseResponse(400,response.getResultCodeDescription());
        //}
        //return null;

        // TODO 需要根据业务自己写
        return null;
    }

    /**
    * 解密响应参数
    * @return
    */
    private String get${product}ResponseData(){
        // TODO 需要根据业务自己写
        return null;
    }

    /**
    * 拼接请求数据
    * @param reqDataJson
    * @param args
    * @return
    */
    private ${product}Request initRequest(String reqDataJson,String... args){
       // TODO 需要根据业务自己写
       return null;
    }

}
