package cn.tuyucheng.taketoday.reflection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TuyuchengReflectionUtilsUnitTest {

	@Test
	public void givenCustomer_whenAFieldIsNull_thenFieldNameInResult() throws Exception {
		Customer customer = new Customer(1, "Himanshu", null, null);

		List<String> result = TuyuchengReflectionUtils.getNullPropertiesList(customer);
		List<String> expectedFieldNames = Arrays.asList("emailId", "phoneNumber");

		assertTrue(result.size() == expectedFieldNames.size());
		assertTrue(result.containsAll(expectedFieldNames));

	}

}
