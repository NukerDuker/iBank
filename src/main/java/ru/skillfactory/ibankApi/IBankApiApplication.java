package ru.skillfactory.ibankApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@SpringBootApplication
@EnableTransactionManagement
public class IBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IBankApiApplication.class, args);

		float mb = 1024f * 1024f;
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

		float xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
		float xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
		System.out.println("Initial Memory (xms) : " + xms + "mb");
		System.out.println("Max Memory (xmx) : " + xmx + "mb");
	}

}
