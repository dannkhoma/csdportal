package zw.hitrac.csdportal.util;

import java.util.Comparator;
import zw.co.hitrac.zhris.csd.adapter.common.domain.WrappedEntity;

/**
 *
 * @author charlesc
 */
public class WrappedEntityComparator implements Comparator<WrappedEntity> {

    @Override
    public int compare(WrappedEntity o1, WrappedEntity o2) {
        if (isPrimaryFacility(o1) && !isPrimaryFacility(o2)) {
            return -1;
        } else if (isDoctor(o1) && !isDoctor(o2)) {
            return -1;
        } else {
            return 0;
        }
    }

    private boolean isPrimaryFacility(WrappedEntity wrappedEntity) {
        boolean primaryFacility = wrappedEntity.getEntityId().contains("urn:mohcc_zimhsp_facility_id:");
        primaryFacility = primaryFacility || wrappedEntity.getEntityId().contains("urn:hpa_facility_id:");
        return primaryFacility;
    }

    private boolean isDoctor(WrappedEntity wrappedEntity) {
        return wrappedEntity.getEntityId().contains("urn:mdpcz_provider_id");
    }

}
