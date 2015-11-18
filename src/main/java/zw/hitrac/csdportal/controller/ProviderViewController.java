package zw.hitrac.csdportal.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.hitrac.jaxcsd.api.domain.CSD;
import zw.co.hitrac.jaxcsd.api.domain.Facility;
import zw.co.hitrac.jaxcsd.api.domain.Provider;
import zw.co.hitrac.jaxcsd.api.domain.ProviderFacility;
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
public class ProviderViewController {

    @RequestMapping(value = "/provider/view", method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "entityId", required = true) String entityId, Model model) {
        Provider provider = getProvider(entityId);
        List<Facility> facilities = facilities(provider, entityId);
        model.addAttribute("provider", provider);
        model.addAttribute("facilities", facilities);
        model.addAttribute("owner", LookupUtility.getOwner(provider.getEntityID()));
        return "provider/provider_view";
    }

    public Provider getProvider(String entityId) {
        CsdQueryClient csdQueryClient = new CsdQueryClient();
        RequestParams requestParams = new RequestParams();
        requestParams.setUniqueID(new Provider(entityId));
        String functionId = "urn:ihe:iti:csd:2014:stored-function:provider-search";
        String httpAddress = UrlUtil.LOCAL_OPENINFOMAN_CSR_URL + LookupUtility.getDirectory(entityId) + "/careServicesRequest";
        CallOptions callOptions = new CallOptions();
        CSD csd = csdQueryClient.callStandardStoredFunction(requestParams, functionId, httpAddress, callOptions);
        List<Provider> providers = csd.getProviderDirectory().getProviders();
        if (providers.isEmpty()) {
            return null;
        } else {
            Provider $provider = providers.get(0);
            return $provider;
        }

    }

    public List<Facility> facilities(Provider provider, String entityId) {
        List<Facility> facilities = new ArrayList<>();

        if (provider.getProviderFacilities() != null) {
            List<ProviderFacility> providerFacilities = provider.getProviderFacilities().getProviderFacilities();

            if (providerFacilities != null && !providerFacilities.isEmpty()) {
                for (ProviderFacility providerFacility : providerFacilities) {
                    Facility facility = getFacility(providerFacility.getEntityID());
                    if (facility != null) {
                        facilities.add(facility);
                    }
                }
            }
        }
        return facilities;
    }

    private Facility getFacility(String entityID) {

        CsdQueryClient csdQueryClient = new CsdQueryClient();
        RequestParams requestParams = new RequestParams();
        System.out.println("Entity ID==" + entityID);
        requestParams.setUniqueID(new Provider(entityID));
        String functionId = "urn:ihe:iti:csd:2014:stored-function:facility-search";
        String httpAddress = UrlUtil.LOCAL_OPENINFOMAN_CSR_URL + LookupUtility.getDirectory(entityID) + "/careServicesRequest";
        CallOptions callOptions = new CallOptions();
        CSD csd = csdQueryClient.callStandardStoredFunction(requestParams, functionId, httpAddress, callOptions);
        List<Facility> facilities = csd.getFacilityDirectory().getFacilities();

        for (Facility facility : facilities) {
            if (facility.getEntityID().equalsIgnoreCase(entityID)) {
                return facility;
            }
        }
        return null;
    }

}
