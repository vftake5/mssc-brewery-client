package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").build();

		UUID uri = breweryClient.saveNewBeer(beerDto);

		assertNotNull(uri);

		System.out.println(uri);
	}

	@Test
	void saveNewBeerW()
	{
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").build();

		UUID uri = breweryClient.saveNewBeerW(beerDto);

		assertNotNull(uri);

		System.out.println(uri);
	}

	@Test
	void updateBeerById()
	{
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").build();

		breweryClient.updateBeerById(beerDto.getId(), beerDto);

	}

	@Test
	void updateBeerByIdW()
	{
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New beer").build();

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