package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository {
	
	private Resident res1;
	private Resident res2;
	private Resident res3;

	public ResidentRepositoryStub() {
		Calendar cal = Calendar.getInstance();
		cal.set(1990, 1, 1);
		res1 = new Resident("Bob", "Thebuilder", "street 1", "city", cal.getTime());
		cal.set(2000, 1, 31);
		res2 = new Resident("Bobby", "Car", "street 2", "town", cal.getTime());
		cal.set(1995, 12, 31);
		res3 = new Resident("Eve", "Newyears", "street 1", "city", cal.getTime());
	}
	
	@Override
	public List<Resident> getResidents() {
		return new ArrayList<Resident>(Arrays.asList(res1, res2, res3));
	}
}
