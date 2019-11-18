package com.example.openweather.model.weather;

import com.example.openweather.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class Daily extends BaseModel {

    public String summary;
    public String icon;
    public List<Data> data = new ArrayList<>();

}
