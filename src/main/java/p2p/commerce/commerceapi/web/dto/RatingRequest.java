package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    private int rating;
    private String ratingDescription;
}
