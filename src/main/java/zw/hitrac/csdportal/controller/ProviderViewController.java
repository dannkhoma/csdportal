package zw.hitrac.csdportal.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.hitrac.jaxcsd.api.domain.CSD;
import zw.co.hitrac.jaxcsd.api.domain.Provider;
import zw.co.hitrac.jaxcsd.api.query.CallOptions;
import zw.co.hitrac.jaxcsd.api.query.CsdQueryClient;
import zw.co.hitrac.jaxcsd.api.query.RequestParams;
import zw.hitrac.csdportal.util.Utility;

/**
 *
 * @author hitrac
 */
@Controller
public class ProviderViewController {

    @RequestMapping(value = "/provider/view", method = RequestMethod.GET)
    public String searchResult(@RequestParam(name = "entityId", required = true) String entityId, Model model) {
        Provider provider = getProvider(entityId);
        model.addAttribute("provider", provider);
        return "provider/provider_view";
    }

    public Provider getProvider(String entityId) {
        CsdQueryClient csdQueryClient = new CsdQueryClient();
        RequestParams requestParams = new RequestParams();
        requestParams.setUniqueID(new Provider(entityId));
        String functionId = "urn:ihe:iti:csd:2014:stored-function:provider-search";
        String httpAddress = "http://192.168.1.22:8984/CSD/csr/" + Utility.getDirectory(entityId) + "/careServicesRequest";
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

}
