package com.springdata.cao.util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JDBCUtilTest {
	@Test
	public void testGetConnection() throws Exception {
		Connection conn = JDBCUtil.getConnection();
		Assert.assertNotNull(conn);
	}
	
}
