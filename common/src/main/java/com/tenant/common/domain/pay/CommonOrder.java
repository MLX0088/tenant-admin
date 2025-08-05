package com.tenant.common.domain.pay;

import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单公共参数
 * 
 * @author 栾钰
 * @date 2023-02-19
 */
public class CommonOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    @NotEmpty(message = "商户订单号不能为空")
    private String orderId;
    /** 支付状态 */
    @Excel(name = "支付状态")
    private Integer status;

    /** 渠道订单id */
    @Excel(name = "渠道订单id")
    private String payOrderId;
    /**
     * 订单金额
     */
    @NotNull(message = "订单金额不能为空")
    private BigDecimal amount;
    /**
     * 返回页面的uri
     */
    private String  returnUri;
    /**
     * 应用类型
     */
    private String appType;
    /** 应用id */
    @Excel(name = "应用id")
    private String appID;
    public enum Status
    {
        /**
         * 未支付
         */
          NO(0),
        /**
         * 成功
         */
         SUCCESS(1),
        /**
         * 失败
         */
          FAIL(2),
        /**
         * 退款
         */
         REFUND(-1);
        private  Integer value;

        Status(Integer value)
        {
            this.value = value;
        }

        public Integer value()
        {
            return this.value;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getReturnUri() {
        return returnUri;
    }

    public void setReturnUri(String returnUri) {
        this.returnUri = returnUri;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    @Override
    public String toString() {
        return "CommonOrder{" +
                "orderId='" + orderId + '\'' +
                ", status=" + status +
                ", payOrderId='" + payOrderId + '\'' +
                ", amount=" + amount +
                ", returnUri='" + returnUri + '\'' +
                ", appType='" + appType + '\'' +
                '}';
    }
}
