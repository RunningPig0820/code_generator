package ${packageName};


import com.moxie.orion.common.processor.ProcessorType;
/**
 * @ClassName
 * @Description
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 **/
public class ${product}${processorType.bigHumpName}Processor extends AbstractCommonProcessor<${processorType.req}, BaseResponse<#if processorType.resp??><${processorType.resp}></#if>> {

    <#list steps as step>
    /**
     * ${step.describe}
    **/
    private static final String ${step.underLineName}_URL = "${step.url}";
    </#list>

    enum RequestStep {
        <#list steps as step>
        //${step.describe}
        ${step.underLineName} <#if !step_has_next>;<#else>,</#if>
        </#list>
    }


    @Override
    public int order() {
        return 0;
    }

    @Override
    public ProcessorType type() {
        return ProcessorType.${processorType.underLineName};
    }

    @Override
    public Object fromReq(Enum requestStep, Object requestParam) {
        Object object = null;
        ${product}${processorType.bigHumpName}Conversion ${processorType.humpName}Conversion = new ${product}${processorType.bigHumpName}Conversion(this);

        switch((RequestStep)requestStep){
            <#list steps as step>
            case ${step.underLineName}:
              object = ${processorType.humpName}Conversion.to${step.bigHumpName}Req(requestParam);
            </#list>
            default:
                break;
        }

        return object;
    }

    @Override
    public Object toRsp(Enum requestStep, Object requestParam, String response) {

        Object object = null;
        ${product}${processorType.bigHumpName}Conversion ${processorType.humpName}Conversion = new ${product}${processorType.bigHumpName}Conversion(this);

        //请求校验
        Object verObject = verificationResponse(response,requestStep);
        if(verObject instanceof BaseResponse){
            return verObject;
        }

        switch((RequestStep)requestStep){
            <#list steps as step>
            case ${step.underLineName}:
              object = ${processorType.humpName}Conversion.to${step.bigHumpName}Resp(requestParam,response);
            </#list>
            default:
                break;
        }

        return object;
    }

    @Override
    public Object process(Object input) {

        String SERVICE_URL = getProductConfigByKey(${product}ProductConfigKey.SERVICE_URL);

        <#list steps as step>
        String ${step.humpName}Url = SERVICE_URL + "/" + ${step.underLineName}_URL;
        Object object = getResponseBody(RequestStep.${step.underLineName},input,${step.humpName}Url,${product}${step.bigHumpName}Req.class);

        if(object instanceof BaseResponse){
            return object;
        }
        </#list>

        <#if processorType.resp??>
        return new BaseResponse<>((${processorType.resp})object);
        <#else>
        return new BaseResponse<>();
        </#if>

    }

}