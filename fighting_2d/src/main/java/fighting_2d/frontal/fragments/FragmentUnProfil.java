package fighting_2d.frontal.fragments;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FragmentUnProfil extends ViewFx {

    @FXML
    private Label labelNomProfil;

    @FXML
    private Label labelVictoireProfil;

    @FXML
    private Label labelDefaiteProfil;

    @Override
    public void initialize() {

        Ntro.assertNotNull(labelNomProfil);
        Ntro.assertNotNull(labelVictoireProfil);
        Ntro.assertNotNull(labelDefaiteProfil);

    }

    public void afficherProfil(String nomProfil, int victoireProfil, int defaiteProfil) {

        labelNomProfil.setText(nomProfil);
        labelVictoireProfil.setText(String.valueOf(victoireProfil));
        labelDefaiteProfil.setText(String.valueOf(defaiteProfil));

    }
}
