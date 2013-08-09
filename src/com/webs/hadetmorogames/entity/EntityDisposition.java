package com.webs.hadetmorogames.entity;


public enum EntityDisposition {


    passive(0, "Passive"),
    passiveaggresive(1, "PassiveAggressive"),
    aggressive(2, "Aggressive");

    public int id;
    public String disposition;

    EntityDisposition (int id, String disposition) {
        this.id = id;
        this.disposition = disposition;
    }

    public int getID () {
        return id;
    }

    public String getDisposition () {
        return disposition;
    }
}
