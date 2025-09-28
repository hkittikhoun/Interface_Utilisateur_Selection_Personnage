package fighting_2d.frontal;

import ca.ntro.app.Ntro;
import ca.ntro.app.session.Session;
import fighting_2d.commun.Region;
import fighting_2d.commun.messages.MsgAjouterProfil;
import fighting_2d.commun.messages.MsgProfilCourant;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.frontal.evenements.EvtAfficherAcceuil;
import fighting_2d.frontal.evenements.EvtAfficherCreationProfil;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.maquettes.MaquetteProfils;

public class SessionFighting2D extends Session<SessionFighting2D> {

    private Class<?> vueCourante = VueCreationProfil.class;

    private Region regionCourante = Region.AMERIQUE;

    public SessionFighting2D() {
        super();
        observerRegionCourante();
    }

    public SessionFighting2D memoriserVueCourante(Class<?> vueCourante) {

        this.vueCourante = vueCourante;

        return this;
    }

    public void envoyerEvtPourAfficherVueCourante() {

        if (vueCourante.equals(VueAcceuil.class)) {

            Ntro.newEvent(EvtAfficherAcceuil.class)
                    .trigger();

        } else {

            Ntro.newEvent(EvtAfficherCreationProfil.class)
                    .trigger();
        }
    }

    public void envoyerMsgAjouterProfil(String nom, int indexPersoSelectionne) {
        if (nom == null || nom.isEmpty()) {
            Ntro.newMessage(MsgAjouterProfil.class)
                    .setPremierProfil(MaquetteProfils.creerProfil(this.sessionId()))
                    .send();
        } else {
            // À adapter selon ta logique pour associer l'index à un caractère
            Ntro.newMessage(MsgAjouterProfil.class)
                    .setPremierProfil(MaquetteProfils.creerprofil(this.sessionId(), nom, indexPersoSelectionne))
                    .send();
        }
    }

    public SessionFighting2D memoriserRegionCourante(Region region) {

        this.regionCourante = region;

        return this;
    }

    public SessionFighting2D observerRegionCourante() {
        this.setModelSelection(ModeleCreationProfil.class, regionCourante.name());

        return this;
    }

    public SessionFighting2D afficherRegionCourante(VueCreationProfil vueCreationProfil) {
        vueCreationProfil.afficherRegionCourante(regionCourante.name());

        return this;
    }
}
