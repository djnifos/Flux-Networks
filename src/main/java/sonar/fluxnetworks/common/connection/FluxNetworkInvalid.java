package sonar.fluxnetworks.common.connection;

import sonar.fluxnetworks.api.network.SecurityType;
import sonar.fluxnetworks.api.misc.FluxConstants;

public class FluxNetworkInvalid extends BasicFluxNetwork {

    public static final FluxNetworkInvalid INSTANCE = new FluxNetworkInvalid();

    private FluxNetworkInvalid() {
        super(FluxConstants.INVALID_NETWORK_ID, "Please select a network", SecurityType.PUBLIC,
                FluxConstants.INVALID_NETWORK_COLOR, FluxConstants.DEFAULT_UUID, null);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
