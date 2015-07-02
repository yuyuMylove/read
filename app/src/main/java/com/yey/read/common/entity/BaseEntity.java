package com.yey.read.common.entity;

import com.lidroid.xutils.db.annotation.Id;

/**
 * Author: sunnie
 * Date: 15-06-02
 * Time: 上午11:15
 */
public  class BaseEntity {

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
