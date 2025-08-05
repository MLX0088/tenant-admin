package com.tenant.common.event;

import com.tenant.common.domain.pay.CommonOrder;
import org.springframework.context.ApplicationEvent;

/**
 * 支付完成事件
 */
import java.time.Clock;
public class PayCompletedEvent extends ApplicationEvent {
    public PayCompletedEvent(CommonOrder source) {
        super(source);
    }

    public PayCompletedEvent(CommonOrder source, Clock clock) {
        super(source, clock);
    }

    @Override
    public CommonOrder getSource() {
        return (CommonOrder)super.getSource();
    }
}
