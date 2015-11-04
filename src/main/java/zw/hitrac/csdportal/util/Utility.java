package zw.hitrac.csdportal.util;

/**
 *
 * @author Charles Chigoriwa
 */
public class Utility {

    public static String getDirectory(String entityID) {
        if (entityID.contains("urn:mohcc_zimhsp_facility_id:")) {
            return "facility_registry";
        } else if (entityID.contains("urn:hpa_facility_id:")) {
            return "facility_registry";
        } else if (entityID.contains("urn:mohcc_hr")) {
            return "mohcc";
        } else if (entityID.contains("urn:ncz_facility_id")) {
            return "facility_registry";
        } else if (entityID.contains("urn:ncz_provider_id")) {
            return "ncz";
        } else if (entityID.contains("urn:ncz_service_id")) {
            return "ncz";
        } else if (entityID.contains("urn:ncz_organiztion_id")) {
            return "facility_registry";
        } else {
            return "unknown";
        }

    }

}
