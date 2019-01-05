package com.cmykui.framework.cmykui.base;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public String Name;
    public double Value;

    public DataSource() {}
    public DataSource(String name, double value){
        this.Name = name;
        this.Value = value;
    }

}
