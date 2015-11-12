package com.standconnect.dummy;

import com.standconnect.Models.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent  implements Serializable{

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<Entity> ITEM_BUSINESS_DUMMY = new ArrayList<Entity>();
    public static ArrayList<Entity> ITEM_PRODUCT_DUMMY = new ArrayList<Entity>();
    public static ArrayList<Entity> ITEM_TAGS_DUMMY = new ArrayList<Entity>();

    static {
        // Add 3 sample items.
        ITEM_BUSINESS_DUMMY.add(new DummyItem("1", "Business1"));
        ITEM_BUSINESS_DUMMY.add(new DummyItem("2", "Business2"));
        ITEM_BUSINESS_DUMMY.add(new DummyItem("3", "Business3"));

        ITEM_PRODUCT_DUMMY.add(new DummyItem("1", "Product1"));
        ITEM_PRODUCT_DUMMY.add(new DummyItem("2", "Product2"));
        ITEM_PRODUCT_DUMMY.add(new DummyItem("3", "Product3"));

        ITEM_TAGS_DUMMY.add(new DummyItem("1", "Tag1"));
        ITEM_TAGS_DUMMY.add(new DummyItem("2", "Tag2"));
        ITEM_TAGS_DUMMY.add(new DummyItem("3", "Tag3"));
    }

}
