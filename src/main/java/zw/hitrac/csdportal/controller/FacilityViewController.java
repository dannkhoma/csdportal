package zw.hitrac.csdportal.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.hitrac.jaxcsd.api.domain.CSD;
import zw.co.hitrac.jaxcsd.api.domain.Facility;
import zw.co.hitrac.jaxcsd.api.domain.Provider;
import zw.co.hitrac.jaxcsd.api.query.CallOptions;
import zw.co.hitrac.jaxcsd.api.query.CsdQueryClient;
import zw.co.hitrac.jaxcsd.api.query.RequestParams;
import zw.co.hitrac.zhris.csd.adapter.common.util.LookupUtility;
import zw.hitrac.csdportal.util.UrlUtil;

/**
 *
 * @author Daniel Nkhoma
 */
@Controller
public class FacilityViewController {

    @RequestMapping(value = "/facility/view", method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "entityId", required = true) String entityId, Model model) {
        Facility facility = getFacility(entityId);
        model.addAttribute("facility", facility);
        model.addAttribute("owner", LookupUtility.getOwner(facility.getEntityID()));
        return "facility/facility_view";
    }

    public Facility getFacility(String entityId) {

        CsdQueryClient csdQueryClient = new CsdQueryClient();
        RequestParams requestParams = new RequestParams();
        System.out.println("Entity ID==" + entityId);
        requestParams.setUniqueID(new Provider(entityId));
        String functionId = "urn:ihe:iti:csd:2014:stored-function:facility-search";
        String httpAddress = UrlUtil.PRODUCTION_OPENINFOMAN_CSR_URL + LookupUtility.getDirectory(entityId) + "/careServicesRequest";
        CallOptions callOptions = new CallOptions();
        CSD csd = csdQueryClient.callStandardStoredFunction(requestParams, functionId, httpAddress, callOptions);
        List<Facility> facilities = csd.getFacilityDirectory().getFacilities();
        if (facilities.isEmpty()) {
            return null;
        } else {
            Facility $facility = facilities.get(0);
            return $facility;
        }

    }

}
