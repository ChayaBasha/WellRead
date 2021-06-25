package com.example.wellread.model;

import android.content.Context;

import com.example.wellread.R;

import java.io.InputStream;
import java.util.Properties;

public class ServiceFactory {
    public static Context context;
    private Properties props;


    private ServiceFactory(Context context) {
        if(context == null) {
            System.out.println("oh nooo");
        }
        this.context = context;
    }


    private static ServiceFactory serviceFactory = null;

    public static ServiceFactory getInstance(Context newContext) {
        if(serviceFactory == null && newContext != null) {
            serviceFactory = new ServiceFactory(newContext);
        }

        return serviceFactory;
    }




    public IService getService(String serviceName) throws ServiceLoadException {
        try {
            Class<?> c = Class.forName(getImplName(serviceName));
            return (IService) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceLoadException(serviceName + " not loaded");
        }
    }

    private String getImplName(String serviceName) throws Exception {
        if (props == null) {
            props = new Properties();

            InputStream fis = context.getResources().openRawResource(R.raw.service_names);
            props.load(fis);
            fis.close();
        }
        return props.getProperty(serviceName);
    }
}
