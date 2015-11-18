package zw.hitrac.csdportal.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.hitrac.zhris.csd.adapter.common.domain.WrappedEntity;
import zw.co.hitrac.zhris.csd.adapter.common.util.EntityFinder;
import zw.hitrac.csdportal.util.UrlUtil;
import zw.hitrac.csdportal.util.WrappedEntityComparator;

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

    @RequestMapping(value = "/search/result", method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "q", required = false) String q, Model model) {
        if (q == null || q.trim().length() < 3) {
            return "index";
        } else {
            String address = UrlUtil.LOCAL_OPENINFOMAN_URL;
            List<WrappedEntity> wrappedEntities = EntityFinder.getEntitiesFromAllDirectories(q, address);
            Collections.sort(wrappedEntities, new WrappedEntityComparator());
            model.addAttribute("wrappedEntities", wrappedEntities);
            model.addAttribute("q", q);
            return "searchResults";
        }
    }

}
