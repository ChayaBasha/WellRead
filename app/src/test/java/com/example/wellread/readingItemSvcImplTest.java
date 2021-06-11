package com.example.wellread;

import com.example.wellread.model.IReadingListSvc;
import com.example.wellread.model.ServiceFactory;
import com.example.wellread.model.ServiceLoadException;
import com.example.wellread.model.readingItemException;
import com.example.wellread.reading.ReadingContent;
import com.example.wellread.reading.Status;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

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

        System.out.println(readingItem1.id + " " + readingItem1.title);

        readingItem2 = new ReadingContent.ReadingItem(
                "The Fashion Cookbook",
                "Hannah Martin",
                "CPR",
                2021,
                Status.OBTAIN);
    }

    @Test
    public void testCreateReadingItem() {
        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.createReadingItem(readingItem1);
            System.out.println("test Create Reading Item passed" + readingItem1.id);
            System.out.println(readingItem1.id.toString());

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

        String readingItemId = readingItem1.id;

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
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

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.updateReadingItem(readingItem1);
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

        try {
            IReadingListSvc readingListService = (IReadingListSvc) serviceFactory.getService(IReadingListSvc.NAME);
            readingListService.deleteReadingItem(readingItem1);
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


