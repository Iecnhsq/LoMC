package service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "IndexServiceInterface")
public class IndexServiceImpl implements IndexServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(IndexServiceImpl.class);

}
