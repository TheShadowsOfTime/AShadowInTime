package com.webs.hadetmorogames.entity.monster;

import com.webs.hadetmorogames.entity.Entity;
import com.webs.hadetmorogames.entity.EntityDisposition;

public abstract class Monster extends Entity {

    public abstract void setDisposition(EntityDisposition disposition);

    public abstract void setAttackTarget(Entity target);
}
