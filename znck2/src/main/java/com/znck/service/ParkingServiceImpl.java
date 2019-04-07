package com.znck.service;

import java.util.List;

import com.znck.service.serviceImpl.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ParkingEntity;
import com.znck.mapper.ParkingMapper;

/**
 * 
 * ParkingServiceImpl
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingMapper parkingMapper;

	@Override
	public ParkingEntity getParkingByCarid(String carId) {
		return parkingMapper.getParkingByCarid(carId);
	}

	@Override
	public List<ParkingEntity> getParkingsByNature(String nature, String order) {
		return parkingMapper.getParkingsByNature(nature, order);
	}

	@Override
	public void save(ParkingEntity parking) {
		this.parkingMapper.insert(parking);
	}

	@Override
	public List<ParkingEntity> getAll() {
		return parkingMapper.getAll();
	}

	@Override
	public ParkingEntity getOne(String id) {
		return parkingMapper.getOne(id);
	}

	@Override
	public void insert(ParkingEntity parking) {
		parkingMapper.insert(parking);
	}

	@Override
	public void update(ParkingEntity parking) {
		parkingMapper.update(parking);
	}

	@Override
	public void delete(String id) {
		parkingMapper.delete(id);
	}

	@Override
	public void truncateTable() {
		// TODO Auto-generated method stub
		parkingMapper.truncateTable();
	}
}
