package service;

import dao.SupportDAOInterface;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "GetSupportPageServiceInterface")
public class GetSupportPageServiceImpl implements GetSupportPageServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(GetSupportPageServiceImpl.class);

    @Resource(name = "SupportDAOInterface")
    private SupportDAOInterface sdao;

    @Override
    public void getListSupport(ModelAndView model) {
        model.addObject("listSupport", sdao.listSupport());
    }

}
