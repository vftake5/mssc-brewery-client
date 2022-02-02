package guru.springframework.msscbreweryclient.web.config;

import guru.springframework.msscbreweryclient.web.properties.RestProperties;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// Akkor kerül használatba, ha visszaállítom a Component állapotát

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer
{

	private final RestProperties properties;

	public BlockingRestTemplateCustomizer(RestProperties properties)
	{
		this.properties = properties;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory()
	{
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(properties.maxTotal());
		connectionManager.setDefaultMaxPerRoute(properties.defaultMaxPerRoute());

		RequestConfig requestConfig = RequestConfig
			.custom()
			.setConnectionRequestTimeout(properties.requestTimeout())
			.setSocketTimeout(properties.socketTimeout())
			.build();

		CloseableHttpClient httpClient = HttpClients
			.custom()
			.setConnectionManager(connectionManager)
			.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
			.setDefaultRequestConfig(requestConfig)
			.build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Override
	public void customize(RestTemplate restTemplate)
	{
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}
}
