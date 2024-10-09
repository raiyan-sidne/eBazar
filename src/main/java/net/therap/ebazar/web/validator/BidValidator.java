package net.therap.ebazar.web.validator;

import net.therap.ebazar.domain.Bid;
import net.therap.ebazar.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author hasin.raiyan
 * @since 4/27/21
 */
@Component
public class BidValidator implements Validator {

    @Autowired
    private BidService bidService;

    private static final double PRECISION_ERROR = 1e-9;
    private static final String PATH = "price";
    private static final String ERROR_CODE = "bid.price.same";
    private static final String SAME_PRICE = "You Already placed the same offer";

    @Override
    public boolean supports(Class<?> clazz) {
        return Bid.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Bid currentBid = (Bid) target;
        Bid previousBid = bidService.getBidById(currentBid.getId());
        double difference = Math.abs(previousBid.getPrice() - currentBid.getPrice());
        if(difference < PRECISION_ERROR) {
            errors.rejectValue(PATH, ERROR_CODE, SAME_PRICE);
        }
    }
}