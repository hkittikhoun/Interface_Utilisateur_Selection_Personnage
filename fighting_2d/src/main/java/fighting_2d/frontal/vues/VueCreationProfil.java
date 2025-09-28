package fighting_2d.frontal.vues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import ca.ntro.app.frontend.ViewLoader;
import fighting_2d.commun.Region;
import fighting_2d.commun.messages.MsgProfilCourant;
import fighting_2d.commun.monde2d.MondeFighting2D;
import fighting_2d.commun.monde2d.PersoFighting2d;
import fighting_2d.commun.valeurs.Profil;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.controles.CanvasCreationProfil;
import fighting_2d.frontal.evenements.EvtAfficherAcceuil;
import fighting_2d.frontal.evenements.EvtChangerRegion;
import fighting_2d.frontal.evenements.EvtSouris;
import fighting_2d.frontal.fragments.FragmentUnProfil;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class VueCreationProfil extends ViewFx {

    @FXML
    private Button boutonRetour;

    @FXML
    private ComboBox<Profil> comboBoxProfils;

    @FXML
    private ComboBox<String> comboRegion;

    private Map<Region, String> nomRegionParRegion = new HashMap<>();
    private Map<String, Region> regionParNomRegion = new HashMap<>();

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button boutonSupprimer;

    @FXML
    private CanvasCreationProfil canvasCreationProfil;

    @FXML
    private TextField textFieldNomProfil;

    private ViewLoader<FragmentUnProfil> viewLoaderUnProfil;

    private List<Pane> lesFragments = new ArrayList<>();

    private PersoFighting2d persoSelectionne;

    private int indexPersoSelectionne = -1;

    @Override
    public void initialize() {

        Ntro.assertNotNull(boutonRetour);
        Ntro.assertNotNull(boutonAjouter);
        Ntro.assertNotNull(comboBoxProfils);
        Ntro.assertNotNull(canvasCreationProfil);
        Ntro.assertNotNull(comboRegion);
        Ntro.assertNotNull(boutonSupprimer);

        supprimerProfilCourant();
        initialiserRegions();
        initialiserComboRegion();
        installerEvtAfficherAcceuil();
        initialiserBoutonAjouter();
        installerEvtSouris();

        comboBoxProfils.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                Ntro.logger().info("[selection]: " + newValue);

                Ntro.newMessage(MsgProfilCourant.class)
                        .setProfilCourant(newValue.intValue())
                        .send();

            }

        });
    }

    private void supprimerProfilCourant() {

        comboBoxProfils.getItems().addListener((ListChangeListener<Profil>) change -> {
            boutonSupprimer.setDisable(comboBoxProfils.getItems().isEmpty());
        });

        boutonSupprimer.setOnAction(evtFx -> {
            int index = comboBoxProfils.getSelectionModel().getSelectedIndex();

            Profil profilSelectionne = comboBoxProfils.getSelectionModel().getSelectedItem();

            if (profilSelectionne != null) {
                profilSelectionne.envoyerMsgRetirerCeProfil(index);
            }
        });

        boutonSupprimer.setDisable(comboBoxProfils.getItems().isEmpty());
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

    private void initialiserRegions() {
        for (Region region : Region.values()) {
            String nomRegion = resources().getString(region.name());
            nomRegionParRegion.put(region, nomRegion);
            regionParNomRegion.put(nomRegion, region);
        }
    }

    private void installerEvtSouris() {
        canvasCreationProfil.onMouseEvent(mouseEventNtro -> {
            Ntro.newEvent(EvtSouris.class)
                    .setMouseEvent(mouseEventNtro)
                    .trigger();
        });
    }

    public void setViewLoaderUnProfil(ViewLoader<FragmentUnProfil> viewLoaderUnProfil) {
        this.viewLoaderUnProfil = viewLoaderUnProfil;
    }

    private class ListCellUnProfil extends ListCell<Profil> {

        @Override
        protected void updateItem(Profil item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setGraphic(null);
            } else {

                FragmentUnProfil fragmentUnProfil = viewLoaderUnProfil.createView();
                fragmentUnProfil.afficherProfil(item.getNom(), item.getNbVictoire(), item.getNbDefaite());
                setGraphic(fragmentUnProfil.rootNode());

                lesFragments.add(fragmentUnProfil.rootNode());

            }
        }
    }

    private void initialiserBoutonAjouter() {
        SessionFighting2D session = Ntro.session();

        boutonAjouter.setOnAction(evtFx -> {
            String nom = textFieldNomProfil.getText();
            if (nom != null && !nom.trim().isEmpty() && indexPersoSelectionne >= 0) {
                session.envoyerMsgAjouterProfil(nom.trim(), indexPersoSelectionne);
            } else if (nom != null && !nom.trim().isEmpty()) {
                session.envoyerMsgAjouterProfil(nom.trim(), -1);
            } else {
                session.envoyerMsgAjouterProfil(null, -1);
            }
        });
    }

    private void installerEvtAfficherAcceuil() {

        boutonRetour.setOnAction(evtFx -> {

            Ntro.newEvent(EvtAfficherAcceuil.class)
                    .trigger();

            Ntro.logger().info("[VueCreationProfil] clic: " + evtFx.getEventType());

        });
    }

    public void initialiserComboProfils() {
        comboBoxProfils.setButtonCell(new ListCellUnProfil());
        comboBoxProfils.setCellFactory(new Callback<ListView<Profil>, ListCell<Profil>>() {

            @Override
            public ListCell<Profil> call(ListView<Profil> p) {
                return new ListCellUnProfil();
            }
        });
    }

    public void afficherProfilEnTexte(List<Profil> listeProfil, int profilCourant) {

        List<Profil> listeNomProfil = new ArrayList<>(listeProfil);
        lesFragments.clear();

        comboBoxProfils.getItems().clear();
        comboBoxProfils.getItems().addAll(listeNomProfil);

        // Sélectionne l'index du profilCourant
        if (!listeNomProfil.isEmpty() && profilCourant >= 0 && profilCourant < listeNomProfil.size()) {
            comboBoxProfils.getSelectionModel().select(profilCourant);
        }

        Platform.runLater(() -> {

            recalculerLargeurs();

        });

    }

    private void recalculerLargeurs() {
        double maxWidth = 0;

        for (Profil profil : comboBoxProfils.getItems()) {

            double width = profil.getNom().length() * 10;

            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        // Ajoutez un peu de marge pour éviter que le texte soit coupé
        comboBoxProfils.setMinWidth(maxWidth + 20);
    }

    public void viderCanvas() {
        canvasCreationProfil.clearCanvas();
    }

    public void afficherImagesParSeconde(String fps) {
        canvasCreationProfil.afficherFps(fps);
    }

    public void afficherFighting2D(MondeFighting2D mondeFighting2D) {
        mondeFighting2D.drawOn(canvasCreationProfil);
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

    public void afficherPersoSelectionne(int index) {
        this.indexPersoSelectionne = index;
    }
}