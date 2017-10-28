package th.co.ais;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ssb.SsbDbServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SsbDbServiceApplication.class)
@WebAppConfiguration
public class SsbDbServiceApplicationTests {

	@Test
	public void contextLoads() {
		
		boolean aaa = StringUtils.startsWith("/barUser/msisdn/123123", "/barUser/msisdn/");
		System.out.println("aaa"+ aaa);
		
		boolean bbb = StringUtils.startsWith("/barUser/msisdn/123123", "/barUser");
		System.out.println("bbb"+ bbb);
		
		StringUtils.indexOfAny("/barUser/msisdn/123123", "/barUser/msisdn/");
		
	}

}
