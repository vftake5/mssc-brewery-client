package guru.springframework.msscbreweryclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto
{

//	@Null
	private UUID id;
//	private Integer version;
//
//	private OffsetDateTime createdDate;
//	private OffsetDateTime lastModifiedDate;

//	@NotBlank
	private String beerName;
//	private BeerStyleEnum beerType;

//	@NotBlank
	private String beerType;

//	@Positive

	private Long upc;

	private BigDecimal price;
//
//	private Integer quantityOnHand;
}
