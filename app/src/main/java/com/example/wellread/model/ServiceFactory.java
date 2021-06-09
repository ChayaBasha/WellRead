package com.example.wellread.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ServiceFactory {
    private Properties props;

    private ServiceFactory() {

    }

    private static ServiceFactory serviceFactory = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public IService getService(String serviceName) throws ServiceLoadException {
        try {
            Class<?> c = Class.forName(getImplName(serviceName));
            return (IService) c.newInstance();
        } catch (Exception e) {
            throw new ServiceLoadException(serviceName + "not loaded");
        }
    }

    private String getImplName(String serviceName) throws Exception {
        if (props == null) {
            props = new Properties();
            FileInputStream fis = new FileInputStream("config/serviceNames.properties");
            props.load(fis);
            fis.close();
        }
        return props.getProperty(serviceName);
    }
}
