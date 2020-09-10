package com.core.webapi.listeners;

import com.core.webapi.model.AlterableEntity;
import com.core.webapi.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AlterationInfoListener {
    private static Logger logger = LoggerFactory.getLogger(AlterationInfoListener.class);
    @PrePersist
    @PreUpdate
    public void setDatesAndUser(Object entity)
    {
        if (entity instanceof AlterableEntity) {
            AlterableEntity alterableEntity = (AlterableEntity) entity;
            String currentUser = SecurityUtils.getCurrentUser();

            if (alterableEntity.getCreatedBy() == null)
                alterableEntity.setCreatedBy(currentUser);

            alterableEntity.setUpdatedBy(currentUser);

            Date now = new Date();
            if (alterableEntity.getDateCreated() == null)
                alterableEntity.setDateCreated(now);

            alterableEntity.setDateUpdated(now);
        } else {
            logger.debug("The entity object [" + entity + "] is not a AlterationInfoBean ");
        }
    }
}
