package zw.hitrac.csdportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/search/result")
    public String searchResult(@RequestParam(name = "searchString", required = false) String searchString, Model model) {
        if (searchString == null) {
            return "index";
        } else {
            return "";
        }
    }

}
