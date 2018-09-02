package com.ninjaclub.dashboard.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ninjaclub.dashboard.model.enums.ResponseCode;

public class CommonUtilTest {

	CommonUtil commonUtil;
	
	@Before
	public void init() {
		commonUtil = new CommonUtil();
	}
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testValidateUserInputIsIntForSuccess() {
		systemInMock.provideLines("5", "another line");
		assertEquals(ResponseCode.SUCCESS, commonUtil.validateUserInputIsInt().getCode());
	}

	@Test
	public void testValidateUserInputIsIntForNotNull() {
		systemInMock.provideLines("55", "another line");
		assertNotNull(commonUtil.validateUserInputIsInt());
	}
}
	

