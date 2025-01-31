package com.phegondev.Phegon.Eccormerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhegonEccormerceApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		// 测试 Spring Boot 上下文加载是否正常
		assertNotNull(context, "Spring Application Context failed to load");
	}

	@Test
	void main() {
		// 通过反射调用 main 方法，确保应用启动没有异常
		try {
			PhegonEccormerceApplication.main(new String[]{});
		} catch (Exception e) {
			fail("Main method threw an exception: " + e.getMessage());
		}
	}

	@Test
	void testBeansLoaded() {
		// 确保核心 Bean 被加载
		assertNotNull(context.getBean(PhegonEccormerceApplication.class), "PhegonEccormerceApplication Bean not loaded");
		// 可以根据项目结构扩展检查其他服务、控制器等
	}
}
