package fighting_2d.frontal.vues;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import fighting_2d.frontal.evenements.EvtAfficherCreationProfil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VueAcceuil extends ViewFx{

    @FXML
    private Button boutonQuitterAcceuil;

    @Override
    public void initialize() {
        
        Ntro.assertNotNull(boutonQuitterAcceuil);

        installerEvtAfficherCreationProfil();
    }

    private void installerEvtAfficherCreationProfil() {
        boutonQuitterAcceuil.setOnAction(evtFx -> {
            
            Ntro.newEvent(EvtAfficherCreationProfil.class)
                .trigger();

            Ntro.logger().info("[VueAcceuil] clic: " + evtFx.getEventType());
        });
    }
}
