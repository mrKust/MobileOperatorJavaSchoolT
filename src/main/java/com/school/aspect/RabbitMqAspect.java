package com.school.aspect;

import com.school.service.contracts.TariffService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This aspect sends updates to MQ server when Tariffs are created or updated
 */
@Component
@Aspect
public class RabbitMqAspect {

    private static final Logger LOG = Logger.getLogger(RabbitMqAspect.class);
    private final TariffService tariffService;

    RabbitMqAspect(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    /**
     * This method log and send notification when new tariff was saved
     */
    @After("execution(* com.school.service.impl.TariffServiceImpl.save(..))")
    public void afterTariffSave() {
        LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about adding new tariff");
        tariffService.notificationAboutTariffUpdate();
    }

    /**
     * This method log and send notification when existed tariff was updated
     */
    @After("execution(* com.school.service.impl.TariffServiceImpl.update(..))")
    public void afterTariffUpdate() throws InterruptedException {
        LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about updating existed tariff");
        Thread.sleep(1000);
        tariffService.notificationAboutTariffUpdate();
    }
}
