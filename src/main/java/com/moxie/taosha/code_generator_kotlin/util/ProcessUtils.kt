package com.moxie.taosha.code_generator_kotlin.util

import com.moxie.taosha.code_generator_kotlin.bean.ProcessType

/**
 * @ClassName ProcessUtils
 * @Description 获取
 * @Author TaoSha
 * @Date 2018/9/11 19:06
 * @Version 1.0
 **/
fun getProcess(enum: String): ProcessType {
    return when(enum){
        //拉取审批结果
        "APPROVE_RESULT_PULL" -> ProcessType("APPROVE_RESULT_PULL","拉取审批结果","OrderNoReq", "OrderApproveInfo")
        //推送绑卡验证码
        "BIND_CARD_CODE" -> ProcessType("BIND_CARD_CODE","推送绑卡验证码","BindCardReq", "BindCardRes")
        //绑卡推送接口
        "BIND_CARD" -> ProcessType("BIND_CARD","绑卡推送接口","BindCardReq", "BindCardRes")
        //获取银行卡列表接口
        "CARD_LIST_PULL" -> ProcessType("CARD_LIST_PULL","获取银行卡列表接口","OrderNoReq", "BankCardListRes")
        //审批确认验证码接口
        "CONFIRM_APPROVAL_CODE" -> ProcessType("CONFIRM_APPROVAL_CODE","审批确认验证码接口","ConfirmApproveReq", null)
        //审批确认接口
        "CONFIRM_APPROVAL" -> ProcessType("CONFIRM_APPROVAL","审批确认接口","ConfirmApproveReq", "CodeConfirmApprovalRes")
        //合同接口
        "CONTRACT_PULL" -> ProcessType("CONTRACT_PULL","合同接口","ContractInfoReq", "List<ContractInfoRes>")
        //动前查询
        "DRAW_PREQUERY_URL" -> ProcessType("DRAW_PREQUERY_URL","动前查询","OrderNoReq", "DrawPrequeryRes")
        //推送活体数据
        "FACE_DATA_PUSH" -> ProcessType("FACE_DATA_PUSH","推送活体数据","FaceDataPushReq", null)
        //订单信息推送接口
        "ORDER_PUSH" -> ProcessType("ORDER_PUSH","订单信息推送接口","PushData", "BaseResponse")
        //拉取订单状态接口
        "ORDER_STATUS_PULL" -> ProcessType("ORDER_STATUS_PULL","拉取订单状态接口","OrderNoReq", "OrderStatusRes")
        //拉取还款详情
        "REPAYMENT_DETAIL_PULL" -> ProcessType("REPAYMENT_DETAIL_PULL","拉取还款详情","RepayDetailReq", "RepaymentDetailRes")
        //还款验证码校验接口
        "REPAYMENT_EXECUTE_CODE" -> ProcessType("REPAYMENT_EXECUTE_CODE","还款验证码校验接口","RepayDetailReq", "RepaymentDetailRes")
        //还款执行
        "REPAYMENT_EXECUTE" -> ProcessType("REPAYMENT_EXECUTE","还款执行","RepayDetailReq", "RepaymentDetailRes")
        //拉取还款计划
        "REPAYMENT_PLAN_PULL" -> ProcessType("REPAYMENT_PLAN_PULL","拉取还款计划","OrderNoReq", "RepaymentPlanRes")
        //获取第三方绑卡跳转地址接口
        "THIRD_BIND_CARD" -> ProcessType("THIRD_BIND_CARD","获取第三方绑卡跳转地址接口","BindCardReq", "ThirdPartyBindCardRes")
        //获取第三方签署合同地址
        "THIRD_CONTRACT_SIGN" -> ProcessType("THIRD_CONTRACT_SIGN","获取第三方签署合同地址","ContractInfoReq", "ThirdPartyContractSignRes")
        //获取第三方还款地址
        "THIRD_REPAYMENT_EXECUTE" -> ProcessType("THIRD_REPAYMENT_EXECUTE","获取第三方还款地址","RepayReq", "ThirdPartyRepayExecuteRes")
        //获取试算结果
        "TRIAL_PULL" -> ProcessType("TRIAL_PULL","获取试算结果","TrailReq", "TrailRes")
        //询问接口
        "USER_CHECK" -> ProcessType("USER_CHECK","询问接口","UserCheckReq", "ApplicableInfoRes")
        //协议
        "PROTOCOL_INFO_PULL" -> ProcessType("PROTOCOL_INFO_PULL","获取协议信息接口","ProtocolInfoReq", "GetProtocolInfoRes")
        //第三方提现
        "THIRD_PARTY_CONFIRM_LOAN" -> ProcessType("THIRD_PARTY_CONFIRM_LOAN","第三方提现","ThirdPartyHandleReq", "ThirdPartyHandleRes")
        else -> ProcessType("",null,null,null)
    }
}