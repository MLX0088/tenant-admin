package com.tenant.common.event;

import com.tenant.common.domain.pay.CommonOrder;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class RefundEvent extends ApplicationEvent {
    public RefundEvent(CommonOrder source) {
        super(source);
    }

    public RefundEvent(CommonOrder source, Clock clock) {
        super(source, clock);
    }
    @Override
    public CommonOrder getSource() {
        return (CommonOrder)super.getSource();
    }
}
