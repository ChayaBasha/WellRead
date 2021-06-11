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

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    protected IService getService(String serviceName) throws ServiceLoadException {
        return serviceFactory.getService(serviceName);
    }

    public static String IREADING_SVC_PROP = "IReadingSvc";

    public IReadingListSvc readingService() throws ServiceLoadException {
        return (IReadingListSvc) getService(IREADING_SVC_PROP);
    }

    public static final List<ReadingItem> ITEMS = new ArrayList<ReadingItem>();


    /**
     * A map of sample Reading items, by ID.
     */
    public static final Map<String, ReadingItem> ITEM_MAP = new HashMap<String, ReadingItem>();

    //    private static final int COUNT = 25;
    private ArrayList<ReadingItem> showReadingItems() throws ServiceLoadException, readingItemException {
        IReadingListSvc readingListService = readingService();
        List<ReadingItem> SAMPLE_DATA = readingListService.getAllReadingItems();
        return Arrays.asList(SAMPLE_DATA);
    };

//            Arrays.asList(
//            new ReadingItem(
//                    "Design Patterns",
//                    "Gang of Four",
//                    "R. Blumenthal",
//                    1995,
//                    Status.TO_READ),
//
//            new ReadingItem(
//                    "Bleakhouse",
//                    "Charles Dickens",
//                    "Prof Thomas",
//                    1852,
//                    Status.TO_READ),
//
//            new ReadingItem(
//                    "The Fashion Cookbook",
//                    "Hannah Martin",
//                    "CPR",
//                    2021,
//                    Status.OBTAIN),
//
//            new ReadingItem(
//                    "The Hitch Hiker's Guide to the Galaxy",
//                    "Douglas Adams",
//                    "R. Blumenthal",
//                    1978,
//                    Status.READ),
//
//            new ReadingItem(
//                    "Computers and Society",
//                    "Lisa Kaczmarczyk",
//                    "M. Goldweber",
//                    2012, Status.TO_READ)));

    static {
        // Add some sample items.
        for (int i = 0; i < SAMPLEDATA.size(); i++) {
            addItem(SAMPLEDATA.get(i));
        }
    }

    private static void addItem(ReadingItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

}
