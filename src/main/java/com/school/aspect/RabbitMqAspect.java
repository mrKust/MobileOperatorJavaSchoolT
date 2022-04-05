package com.school.aspect;

import com.school.service.contracts.TariffService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RabbitMqAspect {

    private static final Logger LOG = Logger.getLogger(RabbitMqAspect.class);
    private final TariffService tariffService;

    RabbitMqAspect(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @After("execution(* com.school.service.impl.TariffServiceImpl.save(..))")
    public void afterTariffSave() {
        this.LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about adding new tariff");
        tariffService.notificationAboutTariffUpdate();
    }

    @After("execution(* com.school.service.impl.TariffServiceImpl.update(..))")
    public void afterTariffUpdate() {
        this.LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about updating existed tariff");
        tariffService.notificationAboutTariffUpdate();
    }
}
