package com.example.wellread.reading;

import com.example.wellread.model.IReadingListSvc;
import com.example.wellread.model.IService;
import com.example.wellread.model.ServiceFactory;
import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Class for Reading Items that are recommended to the user
 * Ideally this would be created by the User
 * This is just an example for now
 */
public class ReadingContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private static ServiceFactory serviceFactory = ServiceFactory.getInstance(null);

    protected static IService getService(String serviceName) throws ServiceLoadException {
        return serviceFactory.getService(serviceName);
    }

    public static String IREADING_SVC_PROP = "IReadingSvc";

    public static IReadingListSvc readingService() throws ServiceLoadException {
        return (IReadingListSvc) getService(IREADING_SVC_PROP);
    }

    /**
     * A map of sample Reading items, by ID.
     */

    //    private static final int COUNT = 25;
    public static List<ReadingItem> getReadingItems() throws ServiceLoadException, readingItemException {
        System.out.println("getReadingItems from Readonging Content. java was called");
        IReadingListSvc readingListService = readingService();
        List<ReadingItem> SAMPLE_DATA = new ArrayList<ReadingItem>(
                readingListService.getAllReadingItems()
        );
        return SAMPLE_DATA;
    }

    public static Map<String, ReadingItem> getItemMap() throws ServiceLoadException, readingItemException {
        System.out.println("getItemMap from ReadingContent.java was called");
        List<ReadingItem> SAMPLE_DATA = getReadingItems();
        Map<String, ReadingItem> itemMap =  new HashMap<>();
        for (int i = 0 ; i < SAMPLE_DATA.size(); i++) {
            ReadingItem item = SAMPLE_DATA.get(i);
            itemMap.put(item.id, item);
        }

        return itemMap;
    }


}
