package zw.hitrac.csdportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Daniel Nkhoma
 */
@Controller
public class Home {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/provider")
    public String provider() {
        return "provider/index";
    }

    @RequestMapping("/facility")
    public String facility() {
        return "facility/index";
    }

    @RequestMapping("/service")
    public String service() {
        return "service/index";
    }

}
