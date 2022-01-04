package guru.springframework.msscbreweryclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto
{
	private UUID id;
//	private Integer version;
//
//	private OffsetDateTime createdDate;
//	private OffsetDateTime lastModifiedDate;

	private String beerName;
//	private BeerStyleEnum beerType;
	private String beerType;
	private Long upc;
//	private BigDecimal price;
//
//	private Integer quantityOnHand;
}
