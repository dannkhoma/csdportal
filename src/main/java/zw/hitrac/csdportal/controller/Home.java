package zw.hitrac.csdportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="/search/result",method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "q", required = false) String q, Model model) {
        if (q == null || q.trim().length()<3) {
            return "index";
        } else {
            model.addAttribute("q", q);
            return "searchResults";
        }
    }

}
