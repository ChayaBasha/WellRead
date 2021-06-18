package com.example.wellread;

import com.example.wellread.model.IReadingListSvc;
import com.example.wellread.model.ServiceFactory;
import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingItem;
import com.example.wellread.reading.Status;

import junit.framework.TestCase;


import org.junit.BeforeClass;
import org.junit.Test;



public class readingItemSvcImplTest extends TestCase {

    private ServiceFactory serviceFactory;
    private ReadingItem readingItem1;
    private ReadingItem readingItem2;
    private ReadingItem readingItem3;
    private ReadingItem readingItem4;

    @BeforeClass
    protected void setUp() throws Exception {
        serviceFactory = ServiceFactory.getInstance();
    }

    @Test
    public void testCreateReadingItem() {

        readingItem1 = new ReadingItem(
                "Design Patterns",
                "Gang of Four",
                "R. Blumenthal",
                1995,
                Status.TO_READ);

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem1);
            System.out.println("test Create Reading Item passed" + readingItem1.id);

        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("Service did not load");
        } catch (readingItemException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testGetAllReadingItems() {

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.getAllReadingItems();
            System.out.println("test get Reading Items passed");

        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("Service did not load");
        } catch (readingItemException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testGetReadingItemById() {

        readingItem2 = new ReadingItem(
                "The Fashion Cookbook",
                "Hannah Martin",
                "CPR",
                2021,
                Status.OBTAIN);

        String readingItemId = readingItem2.id;


        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem2);
            readingListService.getReadingItemById(readingItemId);
            System.out.println("test get Reading Item By ID passed");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("Service did not load");
        } catch (readingItemException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testUpdateReadingItem() {

        readingItem3 = new ReadingItem(
                "Bleakhouse",
                "Charles Dickens",
                "Prof Thomas",
                1852,
                Status.TO_READ);

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem3);
            readingListService.updateReadingItem(readingItem3);
            System.out.println("test update Reading Item By ID passed");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("Service did not load");
        } catch (readingItemException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testDeleteReadingItem() {

        readingItem4 =  new ReadingItem(
                "The Hitch Hiker's Guide to the Galaxy",
                "Douglas Adams",
                "R. Blumenthal",
                1978,
                Status.READ);

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem4);
            readingListService.deleteReadingItem(readingItem4);
            System.out.println("test delete Reading Item passed");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("Service did not load");
        } catch (readingItemException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
}


