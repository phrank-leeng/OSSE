package de.hfu.residents;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceMockTest {
	
	private ResidentRepository mock;
	
	private BaseResidentService bsr = new BaseResidentService();
	
	private Resident src;
		
	private List<Resident> data;
	
	private Calendar cal = Calendar.getInstance();
	
	@Before
	public void setUpResidents() throws Exception {
		src = new Resident();
		cal.set(1990, 1, 1);
		Resident res1 = new Resident("Bob", "Thebuilder", "street 1", "city", cal.getTime());
		cal.set(2000, 1, 31);
		Resident res2 = new Resident("Bobby", "Car", "street 2", "town", cal.getTime());
		cal.set(1995, 12, 31);
		Resident res3 = new Resident("Eve", "Newyears", "street 1", "city", cal.getTime());
		data = Arrays.asList(res1, res2, res3);
		mock = createMock(ResidentRepository.class);
		expect(mock.getResidents()).andReturn(data);
		replay(mock);
		bsr.setResidentRepository(mock);
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testWildCardInGetUniqueResident() throws ResidentServiceException {
		src.setGivenName("B*");
		bsr.getUniqueResident(src);
	}
	
	@Test(expected = ResidentServiceException.class)
	public void testNonUniqueFilterInGetUniqueResident() throws ResidentServiceException {
		src.setCity("city");
		bsr.getUniqueResident(src);
	}
	
	@Test()
	public void testUniqueFilterInGetUniqueResident() throws ResidentServiceException {
		src.setGivenName("Bob");
		assertThat(bsr.getUniqueResident(src), equalTo(data.get(0)));
		verify(mock);
	}
	
	@Test
	public void testDateInGetFilteredResidentsList() {
		cal.set(1995, 12, 31);
		src.setDateOfBirth(cal.getTime());
		bsr.getFilteredResidentsList(src);
		verify(mock);
	}
	
	@Test
	public void testWildCardInGetFilteredResidentsList() {
		src.setGivenName("B*");
		List<Resident> resTarget = Arrays.asList(data.get(0), data.get(1));
		assertThat(bsr.getFilteredResidentsList(src), equalTo(resTarget));
		verify(mock);
	}
	
	@Test
	public void testDuplicateValuesInGetFilteredResidentsList() {
		src.setStreet("street 1");
		List<Resident> resTarget = Arrays.asList(data.get(0), data.get(2));
		assertThat(bsr.getFilteredResidentsList(src), equalTo(resTarget));
		verify(mock);
	}
}
