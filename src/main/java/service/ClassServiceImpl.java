package service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "ClassServiceInterface")
public class ClassServiceImpl implements ClassServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(ClassServiceImpl.class);

}
