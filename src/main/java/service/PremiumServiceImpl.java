package service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "PremiumServiceInterface")
public class PremiumServiceImpl implements PremiumServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(PremiumServiceImpl.class);

}
