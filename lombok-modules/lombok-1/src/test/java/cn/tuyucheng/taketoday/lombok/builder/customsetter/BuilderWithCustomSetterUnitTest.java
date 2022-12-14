package cn.tuyucheng.taketoday.lombok.builder.customsetter;

import org.junit.Test;

import java.io.File;

public class BuilderWithCustomSetterUnitTest {

	@Test
	public void givenBuilderWithCustomSetter_TestTextOnly() {
		Message message = Message.builder()
			.sender("user@somedomain.com")
			.recipient("someuser@otherdomain.com")
			.text("How are you today?")
			.build();
	}

	@Test
	public void givenBuilderWithCustomSetter_TestFileOnly() {
		Message message = Message.builder()
			.sender("user@somedomain.com")
			.recipient("someuser@otherdomain.com")
			.file(new File("/path/to/file"))
			.build();
	}

	@Test(expected = IllegalStateException.class)
	public void givenBuilderWithCustomSetter_TestTextAndFile() {
		Message message = Message.builder()
			.sender("user@somedomain.com")
			.recipient("someuser@otherdomain.com")
			.text("How are you today?")
			.file(new File("/path/to/file"))
			.build();
	}

}
