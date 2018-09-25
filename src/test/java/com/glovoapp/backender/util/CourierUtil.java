package com.glovoapp.backender.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.repository.CourierRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CourierUtil {

	 private static final String COURIERS_FILE = "/data/couriers.json";
	    private static final List<Courier> couriers;

	    static {
	        try (Reader reader = new InputStreamReader(CourierRepository.class.getResourceAsStream(COURIERS_FILE))) {
	            Type type = new TypeToken<List<Courier>>() {
	            }.getType();
	            couriers = new Gson().fromJson(reader, type);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public static Courier findById(String courierId) {
	        return couriers.stream()
	                .filter(courier -> courierId.equals(courier.getId()))
	                .findFirst()
	                .orElse(null);
	    }

	    public List<Courier> findAll() {
	        return new ArrayList<>(couriers);
	    }
	    
	    public static Courier getOne() {
	    	return couriers.get(0);
	    }
}
