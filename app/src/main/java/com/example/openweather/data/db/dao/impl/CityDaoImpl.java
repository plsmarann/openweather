package com.example.openweather.data.db.dao.impl;

import com.example.openweather.data.db.dao.CityDao;
import com.example.openweather.model.City;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class CityDaoImpl extends BaseDaoImpl<City, Integer> implements CityDao {

    public CityDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, City.class);
    }

}
