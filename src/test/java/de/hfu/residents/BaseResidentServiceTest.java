package de.hfu.residents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceTest {
	
	private static ResidentRepository stub = new ResidentRepositoryStub();
	private static BaseResidentService bsr = new BaseResidentService();
	private Resident r;
	private Calendar cal = Calendar.getInstance();
	
	@BeforeClass
	public static void setUpClass() {
		bsr.setResidentRepository(stub);
	}
	
	@Before
	public void setUp() {
		r = new Resident();
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testWildCardInGetUniqueResident() throws ResidentServiceException {
		r.setGivenName("B*");
		bsr.getUniqueResident(r);
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testNonUniqueFilterInGetUniqueResident() throws ResidentServiceException {
		r.setCity("city");
		bsr.getUniqueResident(r);
	}
	
	@Test
	public void testUniqueFilterInGetUniqueResident() throws ResidentServiceException {
		r.setGivenName("Bob");
		assertEquals(bsr.getUniqueResident(r), stub.getResidents().get(0));
	}
	
	@Test
	public void testDateInGetFilteredResidentsList() {
		cal.set(1995, 12, 31);
		r.setDateOfBirth(cal.getTime());
		List<Resident> resTarget = Arrays.asList(stub.getResidents().get(2));
		assertTrue(resTarget.equals(bsr.getFilteredResidentsList(r)));
	}
	
	@Test
	public void testWildCardInGetFilteredResidentsList() {
		r.setGivenName("B*");
		List<Resident> resTarget = stub.getResidents();
		resTarget.remove(2);
		assertTrue(resTarget.equals(bsr.getFilteredResidentsList(r)));
	}
	
	@Test
	public void testDuplicateValuesInGetFilteredResidentsList() {
		r.setStreet("street 1");
		List<Resident> resTarget = stub.getResidents();
		resTarget.remove(1);
		assertTrue(resTarget.equals(bsr.getFilteredResidentsList(r)));
	}
	
}
