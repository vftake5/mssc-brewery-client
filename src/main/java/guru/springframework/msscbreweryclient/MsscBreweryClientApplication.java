package guru.springframework.msscbreweryclient;

import guru.springframework.msscbreweryclient.web.properties.RestProperties;
import guru.springframework.msscbreweryclient.web.properties.ServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RestProperties.class, ServiceProperties.class })
public class MsscBreweryClientApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(MsscBreweryClientApplication.class, args);
	}

}
