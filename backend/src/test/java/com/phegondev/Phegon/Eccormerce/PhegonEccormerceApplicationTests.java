package com.phegondev.Phegon.Eccormerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
<<<<<<< HEAD
class PhegonEccormerceApplicationTests {

	@Test
	void contextLoads() {
	}

}
=======
class PhegonEccormerceApplicationTest {

	@Test
	public void contextLoads() {
		// 检查 Spring Boot 应用程序是否能够正常启动
	}

	@Test
	public void main() {
		// 测试 main 方法，确保没有抛出异常
		String[] args = {};
		PhegonEccormerceApplication.main(args);
	}
}
>>>>>>> 851b1e8 (delete secrete msg)
