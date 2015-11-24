package com.standconnect.dummy;

import com.standconnect.Models.Bussines;
import com.standconnect.Models.Entity;
import com.standconnect.Models.Product;
import com.standconnect.Models.Tag;

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
        ITEM_BUSINESS_DUMMY.add(new Bussines("RosRoca",
                "RosRoca Description",
                2,
                "http://www.cloudsoftwareprogram.org/rs/374/e9c4455d-a317-4f4c-9f70-108d736bae98/412/filename/cloud-business.jpg",
                "Contact",
                "609680918",
                "C/Piruleta Pais de las golosinas"));
        ITEM_BUSINESS_DUMMY.add(new Bussines("Matrix",
                "Matrix Description",
                4,
                "https://farm6.static.flickr.com/5525/9045254666_a29652749b_b.jpg",
                "Contact Matrix",
                "609999999",
                "C/Regaliz Pais de las golosinas"));
        ITEM_BUSINESS_DUMMY.add(new Bussines("WOK",
                "WOK Description",
                5,
                "http://www.englishforbusinesscommunication.com/wp-content/uploads/2013/06/BusinessHandShake.jpg",
                "Contact WOK",
                "609680918",
                "C/Nube Pais de las golosinas"));

        ITEM_PRODUCT_DUMMY.add(new Product("Regaliz dulce",
                "Regaliz Enrollado",
                2.05,
                "http://www.netaldea.es/files/2013/01/Regaliz-dulce-medicina.jpg"));
        ITEM_PRODUCT_DUMMY.add(new Product("Nube",
                "Nube Dulce",
                5.05,
                "http://www.gastronomiaycia.com/wp-content/uploads/2011/12/malvaviscos_nubes.jpg"));
        ITEM_PRODUCT_DUMMY.add(new Product("Regaliz",
                "Regaliz de campo",
                4.05,
                "http://www.botanical-online.com/fotos/plantasmedicinales/regaliz_regalessia3.jpeg"));

        ITEM_TAGS_DUMMY.add(new Tag("Dulce"));
        ITEM_TAGS_DUMMY.add(new Tag("Salado"));
        ITEM_TAGS_DUMMY.add(new Tag("Picante"));
    }

}
