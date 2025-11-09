package co.appointment.constant;

import co.appointment.shared.constant.RoleConstants;

public interface AuthorityConstants {
    String ADMINISTRATOR_AUTHORITY = String.format("ROLE_%s", RoleConstants.ADMIN_ROLE);
    String CUSTOMER_AUTHORITY = String.format("ROLE_%s", RoleConstants.CUSTOMER_ROLE);
}
