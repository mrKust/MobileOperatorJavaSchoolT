package com.school.aspect;

import com.school.controller.AdvRestController;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RabbitMqAspect {

    private static final Logger LOG = Logger.getLogger(RabbitMqAspect.class);
    private final AdvRestController advRestController;

    RabbitMqAspect(AdvRestController advRestController) {
        this.advRestController = advRestController;
    }

    @After("execution(* com.school.service.impl.TariffServiceImpl.save(..))")
    public void afterTariffSave() {
        this.LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about adding new tariff");
        advRestController.notificationAboutUpdate();
    }

    @After("execution(* com.school.service.impl.TariffServiceImpl.update(..))")
    public void afterTariffUpdate() {
        this.LOG.setLevel(Level.TRACE);
        LOG.trace("Send notification about updating existed tariff");
        advRestController.notificationAboutUpdate();
    }
}
