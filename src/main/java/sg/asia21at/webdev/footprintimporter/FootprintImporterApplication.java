package sg.asia21at.webdev.footprintimporter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("sg.asia21at.webdev.footprintimporter.mybatis.mapper")

public class FootprintImporterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(FootprintImporterApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}
	
	@Override
    public void run(String... args) throws Exception {

        System.out.println("Hello world!");

    }
}
