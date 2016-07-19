package com.beatnikstree.digitalocean;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DigitaloceanApplication.class)
public class DigitaloceanApplicationTests {

	@Autowired
	private DigitalOcean digitalOcean;

	@Test
	public void contextLoads() {
		Assert.notNull(digitalOcean);
	}

	@Test
	public void doSomething() throws RequestUnsuccessfulException, DigitalOceanException {
		Assert.notNull(digitalOcean);
		Account account = digitalOcean.getAccountInfo();
		Assert.isTrue("nullpointer0x00@gmail.com".equals(account.getEmail()));
	}

}
