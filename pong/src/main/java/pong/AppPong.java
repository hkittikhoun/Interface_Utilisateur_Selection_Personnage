package pong;

import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import pong.commun.Declarations;
import pong.dorsal.DorsalPong;
import pong.frontal.FrontalPong;

public class AppPong implements NtroAppFx {
    public static void main(String[] args) {
        NtroAppFx.launch(args);
    }

    @Override
    public void registerFrontend(FrontendRegistrarFx registrar) {
        registrar.registerFrontend(FrontalPong.class);
    }

    @Override
    public void registerModels(ModelRegistrar registrar) {

        Declarations.declarerModeles(registrar);
    }

    @Override
    public void registerMessages(MessageRegistrar registrar) {

        Declarations.declarerMessages(registrar);

    }

    @Override
    public void registerBackend(BackendRegistrar registrar) {

        registrar.registerBackend(DorsalPong.class);

    }

}
