package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest
{

	@Autowired
	BreweryClient breweryClient;

	@Test
	void getBeerById()
	{
		BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

		assertNotNull(beerDto);
	}

	@Test
	void getBeerByIdW()
	{
		BeerDto beerDto = breweryClient.getBeerByIdW(UUID.randomUUID());

		assertNotNull(beerDto);
	}


	@Test
	void saveNewBeer()
	{
//		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").upc(1L).build();
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").build();

		UUID uri = breweryClient.saveNewBeer(beerDto);

		assertNotNull(uri);

		System.out.println(uri);
	}

	@Test
	void saveNewBeerW()
	{
		BeerDto beerDto = BeerDto.builder().beerName("New beer").upc(1L).beerType("PILSNER").price(new BigDecimal("125")).build();

		try
		{
			URI uri = breweryClient.saveNewBeerW(beerDto);

			assertNotNull(uri);

			System.out.println(uri);
		}
		catch (WebClientResponseException er)
		{
			System.out.println(er.getResponseBodyAsString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Test
	void saveNewBeerWParamError()
	{
		BeerDto beerDto = BeerDto.builder().beerName("New beer").build();

		try
		{
			URI uri = breweryClient.saveNewBeerW(beerDto);

			assert(false);
		}
		catch (WebClientResponseException er)
		{
			System.out.println(er.getResponseBodyAsString());

			assert(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assert(false);
		}


		beerDto = BeerDto.builder().beerName("New beer").price(new BigDecimal("125")).build();

		try
		{
			URI uri = breweryClient.saveNewBeerW(beerDto);

			assert(false);
		}
		catch (WebClientResponseException er)
		{
			System.out.println(er.getResponseBodyAsString());

			assert(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assert(false);
		}

		beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").price(new BigDecimal("125")).upc(1L).build();

		try
		{
			URI uri = breweryClient.saveNewBeerW(beerDto);

			assert(false);
		}
		catch (WebClientResponseException er)
		{
			System.out.println(er.getResponseBodyAsString());

			assert(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assert(false);
		}

	}


	@Test
	void updateBeerById()
	{
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").upc(1L).build();

		breweryClient.updateBeerById(beerDto.getId(), beerDto);

	}

	@Test
	void updateBeerByIdW()
	{
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").upc(1L).build();

		breweryClient.updateBeerByIdW(beerDto.getId(), beerDto);

	}

	@Test
	void deleteBeer()
	{
		breweryClient.deleteBeer(UUID.randomUUID());
	}

	@Test
	void deleteBeerW()
	{
		breweryClient.deleteBeerW(UUID.randomUUID());
	}
}