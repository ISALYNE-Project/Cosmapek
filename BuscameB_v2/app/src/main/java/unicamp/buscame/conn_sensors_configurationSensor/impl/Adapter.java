package unicamp.buscame.conn_sensors_configurationSensor.impl;

import unicamp.buscame.conn_sensors_configurationSensor.prov.IManager;
import unicamp.buscame.util.representation.CompanyList;

class Adapter
    implements unicamp.buscame.configurationSensor.req.ISensorUpdater, unicamp.buscame.conn_sensors_configurationSensor.impl.IInterfaceTags {
    private IManager manager;

    Adapter(IManager manager) {
        this.manager = manager;
    }


    @Override
    public synchronized boolean activateSensor(String name) {
        mapekcosmos.sensors.prov.ISensorUpdater iSensorUpdater =
                (mapekcosmos.sensors.prov.ISensorUpdater) this.manager.getRequiredInterface(ISensorUpdaterTag);
        return iSensorUpdater.activateSensor(name);
    }

    @Override
    public synchronized boolean deactivateSensor(String name) {
        mapekcosmos.sensors.prov.ISensorUpdater iSensorUpdater =
                (mapekcosmos.sensors.prov.ISensorUpdater) this.manager.getRequiredInterface(ISensorUpdaterTag);
        return iSensorUpdater.deactivateSensor(name);
    }
}
