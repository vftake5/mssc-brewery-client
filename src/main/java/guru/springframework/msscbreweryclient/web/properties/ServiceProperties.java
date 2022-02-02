package guru.springframework.msscbreweryclient.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sfg.brewery")
public record ServiceProperties(String apihost, String customerV1, String namePath, String savePath, String updatePath, String deletePath)
{
}
