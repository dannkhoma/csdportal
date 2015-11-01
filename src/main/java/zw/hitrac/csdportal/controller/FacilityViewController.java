package zw.hitrac.csdportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.hitrac.jaxcsd.api.domain.Facility;

/**
 *
 * @author hitrac
 */
@Controller
public class FacilityViewController {

    @RequestMapping(value = "/facility/view", method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "entityId", required = true) String entityId, Model model) {
        Facility facility = getFacility(entityId);
        model.addAttribute("facility", facility);
        return "facility/facility_view";
    }

    public Facility getFacility(String entityId) {

        Facility facility = new Facility();
        return facility;

    }

}
