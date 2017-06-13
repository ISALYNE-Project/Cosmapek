package unicamp.buscame.localizationSensor.core;

import android.util.Log;

import unicamp.buscame.localizationSensor.prov.IManager;

import unicamp.buscame.localizationSensor.req.ILocalizationManager;
import unicamp.buscame.localizationSensor.req.ISensorUpdater;

/**
 * @author Junior Cupe Casquina
 */
public class SensorUpdaterM {
    private IManager manager;
    private long timeLapse;
    private String nome;
    private ILocalizationManager localizationManager;
    private ISensorUpdater sensorUpdater;
    private boolean turnOn;


    public SensorUpdaterM(IManager manager) {
        this.manager = manager;
        this.timeLapse = 10000;
        this.turnOn = false;
        this.nome = "LocalizationSensor";
    }

    private void getManagers() {
        this.localizationManager =
                (ILocalizationManager) manager.getRequiredInterface("ILocalizationManager");
        this.sensorUpdater =
                (ISensorUpdater) manager.getRequiredInterface("ISensorUpdater");
    }

    public synchronized boolean runSensor() {
        this.turnOn = true;
            while (this.turnOn) {
                try {
                    Thread.sleep(timeLapse);
                    this.executeSensor();
                }
                catch (Exception e){
                //    sensorUpdater.activateSensor(nome);
                    Log.d("Buscame", "Error = Localization A Sensor " + e.toString());
                }
            }
        return true;
    }

    private synchronized void executeSensor() {
        getManagers();
        try {
            //performance true and false
            long startTime = System.currentTimeMillis();
            localizationManager.getCompanyList(10.0,10.0,10);
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(totalTime>100000){
                long timeSensorI= System.currentTimeMillis();
                Log.d("Buscame", "Localization A Sensor(Activated)I: "+timeSensorI+" " + nome + " Time: " + totalTime + " ");

                sensorUpdater.activateSensor(nome);

                long timeSensorF= System.currentTimeMillis();
                Log.d("Buscame", "Localization A Sensor(Activated)F: " + timeSensorF + " " + nome + " Time: " + totalTime + " ");

            }else{
                long timeSensorI= System.currentTimeMillis();
                android.util.Log.i("Buscame", "Localization A Sensor(Deactivated)I: " + timeSensorI + " ");

                sensorUpdater.deactivateSensor(nome);

                long timeSensorF= System.currentTimeMillis();
                android.util.Log.i("Buscame", "Localization A Sensor(Deactivated)F: " + timeSensorF + " ");
            }
        }
        catch (Exception e){
            Log.d("Buscame", "Localization A Sensor Catch -> " + e.toString());
            long timeSensorI= System.currentTimeMillis();
            android.util.Log.i("Buscame", "Localization A Sensor(Activated)I: " + timeSensorI + " ");
            sensorUpdater.activateSensor(nome);

            long timeSensorF= System.currentTimeMillis();
            android.util.Log.i("Buscame", "Localization A Sensor(Activated)F: " + timeSensorF + " ");
        }
    }

    public synchronized boolean deactivateSensor() {
        getManagers();
        this.turnOn = false;
        return true;
    }
}