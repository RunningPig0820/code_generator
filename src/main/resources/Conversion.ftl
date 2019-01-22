<#macro createBean sourceClassList>
    <#list sourceClassList as sourceClass>
        ${sourceClass.bigHumpName} ${sourceClass.humpName} = new ${sourceClass.bigHumpName}();
        <#if sourceClass.sourseList?? && (sourceClass.sourseList?size > 0) >
        <#else>
            <@createBean sourceClassList=sourceClass.sourseList/>
        </#if>
    </#list>
</#macro>

package ${packageName};

/**
 * @ClassName WanGuanJieApproveResultPullConversion
 * @Description TODO
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 **/
public class ${product}${processorType.bigHumpName}Conversion {

    private AbstractCommonProcessor abstractCommonProcessor = null;

    public WanGuanJieApproveResultPullConversion(AbstractCommonProcessor abstractCommonProcessor) {
        this.abstractCommonProcessor = abstractCommonProcessor;
    }


    public Object to${bean.bigHumpName}Req(Object requestParam) {
        ${sourceClassName.bigHumpName} ${sourceClassName.humpName} = ${sourceClassName.bigHumpName}requestParam;
        <@createBean sourceClassList=sourceClassName.sourseList/>

        <#list conversionClassName.conversionPropertyList as conversionProperty>
            ${conversionProperty}:
        </#list>

        ${conversionClassName.bigHumpName} ${conversionClassName.humpName} = new ${conversionClassName.bigHumpName}();
        <#list conversionClassName.propertyList as property>
            ${property}:
        </#list>

        return ${conversionClassName.humpName};
    }

    <#--public Object to${bean.bigHumpName}Resp(Object requestParam, String response) {-->
        <#--WanGuanJieOrderInfoResp wanGuanJieOrderInfoResp = (WanGuanJieOrderInfoResp)abstractCommonProcessor.getBean(response,WanGuanJieOrderInfoResp.class,false);-->
        <#--return getOrderApproveInfo(wanGuanJieOrderInfoResp);-->
    <#--}-->

    <#--public Object get${sourceClassName}Info(WanGuanJieOrderInfoResp wanGuanJieOrderInfoResp){-->
        <#--String orderNo = wanGuanJieOrderInfoResp.getOrderId();-->

        <#--Integer conclusion = ConclusionStatusEnum.CONCLUSION_FAIL.getType();-->
        <#--if(WanGuanJieOrderStatusEnum.AUDIT_PASS.getType().equalsIgnoreCase(wanGuanJieOrderInfoResp.getStatus())){-->
            <#--conclusion = ConclusionStatusEnum.CONCLUSION_SUCCESS.getType();-->
        <#--}-->

        <#--Long approvalTime = System.currentTimeMillis()/1000;-->
        <#--Integer amountType = AmountTypeEnum.FIXED_AMOUNT.getType();-->
        <#--Long rangeAmount = 0L;-->
        <#--Integer termType = TermTypeEnum.FIXED_TERM.getType();-->
        <#--Integer termUnit = ApprovalTermUnitEnum.DAY.getType();-->

        <#--String loanTermOption = "["+wanGuanJieOrderInfoResp.getApprovalDays()+"]";-->

        <#--Long minLoanAmount = BaseNumberUtils.yuanToFen(wanGuanJieOrderInfoResp.getApprovalAmount()) ;-->
        <#--Long maxLoanAmount = BaseNumberUtils.yuanToFen(wanGuanJieOrderInfoResp.getApprovalAmount()) ;-->
        <#--Long approvalAmount = BaseNumberUtils.yuanToFen(wanGuanJieOrderInfoResp.getApprovalAmount());-->
        <#--Integer approvalTerm = Integer.parseInt(wanGuanJieOrderInfoResp.getApprovalDays());-->

        <#--OrderApproveInfo orderApproveInfo = new OrderApproveInfo();-->
        <#--orderApproveInfo.setOrderNo(orderNo);-->
        <#--orderApproveInfo.setConclusion(conclusion);-->
        <#--orderApproveInfo.setApprovalTime(approvalTime);-->
        <#--orderApproveInfo.setAmountType(amountType);-->
        <#--orderApproveInfo.setMinLoanAmount(minLoanAmount);-->
        <#--orderApproveInfo.setMaxLoanAmount(maxLoanAmount);-->
        <#--orderApproveInfo.setRangeAmount(rangeAmount);-->
        <#--orderApproveInfo.setApprovalAmount(approvalAmount);-->
        <#--orderApproveInfo.setTermType(termType);-->
        <#--orderApproveInfo.setTermUnit(termUnit);-->
        <#--orderApproveInfo.setApprovalTerm(approvalTerm);-->
        <#--orderApproveInfo.setLoanTermOption(loanTermOption);-->
        <#--orderApproveInfo.setRemark("借款额度："+approvalAmount+ " 审批天数 "+ loanTermOption);-->
        <#--return orderApproveInfo;-->
    <#--}-->
}