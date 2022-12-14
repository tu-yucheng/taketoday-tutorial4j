package cn.tuyucheng.taketoday.mappingexception;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class JacksonMappingExceptionUnitTest {

	@Test(expected = JsonMappingException.class)
	public final void givenObjectHasNoAccessors_whenSerializing_thenException() throws JsonParseException, IOException {
		final String dtoAsString = new ObjectMapper().writeValueAsString(new MyDtoNoAccessors());

		assertThat(dtoAsString, notNullValue());
	}

	@Test
	public final void givenObjectHasNoAccessors_whenSerializingWithPrivateFieldsVisibility_thenNoException() throws JsonParseException, IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		final String dtoAsString = objectMapper.writeValueAsString(new MyDtoNoAccessors());

		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, containsString("stringValue"));
		assertThat(dtoAsString, containsString("booleanValue"));
	}

	@Test
	public final void givenObjectHasNoAccessorsButHasVisibleFields_whenSerializing_thenNoException() throws JsonParseException, IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		final String dtoAsString = objectMapper.writeValueAsString(new MyDtoNoAccessorsAndFieldVisibility());

		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, containsString("stringValue"));
		assertThat(dtoAsString, containsString("booleanValue"));
	}

}
