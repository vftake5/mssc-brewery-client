package guru.springframework.msscbreweryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MsscBreweryClientApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(MsscBreweryClientApplication.class, args);
	}

}
