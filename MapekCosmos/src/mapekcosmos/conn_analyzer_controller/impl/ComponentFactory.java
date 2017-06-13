package mapekcosmos.conn_analyzer_controller.impl;


import mapekcosmos.conn_analyzer_controller.prov.IManager;

/**
 * Created by ArchyWin on 5/25/2015.
 */
public class ComponentFactory {
    private static IManager manager = null;

    private ComponentFactory() {
    }

    public static synchronized IManager createInstance() {
        if (manager == null)
            manager = new mapekcosmos.conn_analyzer_controller.impl.Manager();
        return manager;
    }
}
