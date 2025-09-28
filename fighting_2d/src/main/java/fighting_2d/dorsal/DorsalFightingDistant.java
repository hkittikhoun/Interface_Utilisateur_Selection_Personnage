package fighting_2d.dorsal;

import ca.ntro.app.backend.RemoteBackendNtro;
import ca.ntro.app.common.ServerRegistrar;
import fighting_2d.commun.Declarations;

public class DorsalFightingDistant extends RemoteBackendNtro {

    @Override
    public void registerServer(ServerRegistrar registrar) {
        Declarations.declarerServeur(registrar);
    }
}
