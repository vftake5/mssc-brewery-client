package guru.springframework.msscbreweryclient.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Ez csak egy lehetséges property beállítás. Csak java.version=17-tel működik. Használata a BlockingRestTemplateCustomizer-ben
 */

@ConfigurationProperties(prefix = "sfg.resttemplate")
public record RestProperties(int maxTotal, int defaultMaxPerRoute, int requestTimeout, int socketTimeout)
{
}
