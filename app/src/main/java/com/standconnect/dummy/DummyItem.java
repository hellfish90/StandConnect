package com.standconnect.dummy;

/**
 * Created by Marc on 9/11/15.
 */

import com.standconnect.Models.Entity;

import java.io.Serializable;

/**
 * A dummy item representing a piece of content.
 */
public  class DummyItem  implements Serializable, Entity {
    public String id;
    public String content;

    public DummyItem(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public String getName() {
        return content;
    }
}