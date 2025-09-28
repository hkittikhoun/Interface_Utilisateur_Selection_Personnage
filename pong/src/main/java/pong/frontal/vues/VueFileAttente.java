package pong.frontal.vues;

import static ca.ntro.app.tasks.frontend.FrontendTasks.session;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.fx.controls.ResizableImage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import pong.commun.enums.Region;
import pong.commun.valeurs.RendezVous;
import pong.frontal.SessionPong;
import pong.frontal.evenements.EvtAfficherPartie;
import pong.frontal.evenements.EvtChangerRegion;
import pong.frontal.fragments.FragmentRendezVous;
import pong.frontal.fragments.FragmentRendezVousComplet;

public class VueFileAttente extends ViewFx {

    private ViewLoader<FragmentRendezVous> viewLoaderRendezVous;

    public ViewLoader<FragmentRendezVous> getViewLoaderRendezVous() {
        return viewLoaderRendezVous;
    }

    public void setViewLoaderRendezVous(ViewLoader<FragmentRendezVous> viewLoaderRendezVous) {
        this.viewLoaderRendezVous = viewLoaderRendezVous;
    }

    public ViewLoader<FragmentRendezVousComplet> getViewLoaderRendezVousComplet() {
        return viewLoaderRendezVousComplet;
    }

    public void setViewLoaderRendezVousComplet(ViewLoader<FragmentRendezVousComplet> viewLoaderRendezVousComplet) {
        this.viewLoaderRendezVousComplet = viewLoaderRendezVousComplet;
    }

    private ViewLoader<FragmentRendezVousComplet> viewLoaderRendezVousComplet;

    @FXML
    private Button boutonSInscrire;

    @FXML
    private VBox conteneurRendezVous;

    @FXML
    private ResizableImage logo;

    @FXML
    private ComboBox<String> comboRegion;

    private Map<Region, String> nomRegionParRegion = new HashMap<>();
    private Map<String, Region> regionParNomRegion = new HashMap<>();

    @Override
    public void initialize() {

        Ntro.assertNotNull(boutonSInscrire);
        Ntro.assertNotNull(conteneurRendezVous);
        Ntro.assertNotNull(logo);
        Ntro.assertNotNull(comboRegion);

        logo.setImage(new Image("/images/logo.png"));

        // si ResizableAvatar
        // logo.setBackgroundColor(Color.web("#ffff7a"));

        initialiserRegions();

        initialiserComboRegion();

        initialiserBoutonSInscrire();
    }

    private void initialiserRegions() {
        for (Region region : Region.values()) {
            String nomRegion = resources().getString(region.name());
            nomRegionParRegion.put(region, nomRegion);
            regionParNomRegion.put(nomRegion, region);
        }
    }

    private void initialiserComboRegion() {
        comboRegion.setFocusTraversable(false);

        for (Region region : Region.values()) {
            comboRegion.getItems().add(nomRegionParRegion.get(region));
        }

        comboRegion.setOnAction(evtFx -> actionComboRegion());

    }

    private void actionComboRegion() {

        String nomRegion = comboRegion.getSelectionModel().getSelectedItem();

        Region region = regionParNomRegion.get(nomRegion);

        if (region != null) {

            Ntro.newEvent(EvtChangerRegion.class)
                    .setRegion(region)
                    .trigger();
        }
    }

    public void afficherRendezVousEnTexte(String texteRendezVous) {
        // RIEN
    }

    private void initialiserBoutonSInscrire() {

        SessionPong session = Ntro.session();
        boutonSInscrire.setOnAction(evtFx -> {

            session.envoyerMsgAjouterRendezVous();

        });
    }

    public void ajouterRendezVous(RendezVous rendezVous) {
        FragmentRendezVous fragment = rendezVous.creerFragment(viewLoaderRendezVous, viewLoaderRendezVousComplet);
        rendezVous.afficherSur(fragment);

        conteneurRendezVous.getChildren().add(fragment.rootNode());
    }

    public void viderListeRendezVous() {
        conteneurRendezVous.getChildren().clear();
    }

    public void afficherRegionCourante(String idRegion) {
        Region region = null;

        if (idRegion != null) {
            region = Region.valueOf(idRegion);
        }

        if (region == null) {

            comboRegion.getSelectionModel().clearSelection();

        } else {

            comboRegion.getSelectionModel().select(nomRegionParRegion.get(region));

        }
    }
}
