package com.example.wellread;

import com.example.wellread.model.IReadingListSvc;
import com.example.wellread.model.ServiceFactory;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.Status;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class readingItemSvcImplTest extends TestCase {

    private ServiceFactory serviceFactory;
    private ReadingContent.ReadingItem readingItem1;
    private ReadingContent.ReadingItem readingItem2;

    @Before
    protected void setUp() throws Exception {
        serviceFactory = ServiceFactory.getInstance();

        readingItem1 = new ReadingContent.ReadingItem(
                "Design Patterns",
                "Gang of Four",
                "R. Blumenthal",
                1995,
                Status.TO_READ);

        readingItem2 = new ReadingContent.ReadingItem(
                "The Fashion Cookbook",
                "Hannah Martin",
                "CPR",
                2021,
                Status.OBTAIN);
    }

    @Test
    public void testCreateReadingItem() {
        try{
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem1);
            System.out.println("testCreate Reading Item passed");

        }
    }
}
