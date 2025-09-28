package pong.commun.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ntro.app.models.Model;
import ca.ntro.app.models.WatchJson;
import ca.ntro.app.models.WriteObjectGraph;
import pong.commun.enums.Position;
import pong.commun.enums.Region;
import pong.commun.valeurs.Joueur;
import pong.commun.valeurs.RendezVous;
import pong.commun.valeurs.RendezVousComplet;
import pong.frontal.vues.VueFileAttente;

public class ModeleFileAttente implements Model, WatchJson, WriteObjectGraph {

    private long prochainIdRendezVous = 1;
    private List<RendezVous> rendezVousDansOrdre = new ArrayList<>();
    private Region region = null;

    public ModeleFileAttente() {

    }

    public void initialiserRegion(Region region) {
        this.region = region;
    }

    public void afficherSur(VueFileAttente vueFileAttente) {
        vueFileAttente.viderListeRendezVous();

        for (RendezVous rendezVous : rendezVousDansOrdre) {

            vueFileAttente.ajouterRendezVous(rendezVous);
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        int numeroRendezVous = 1;

        for (RendezVous rendezVous : rendezVousDansOrdre) {

            builder.append(numeroRendezVous);
            builder.append(". ");
            builder.append(rendezVous.toString());
            builder.append("\n");

            numeroRendezVous++;
        }

        return builder.toString();
    }

    public void ajouterRendezVous(Joueur premierJoueur) {

        String idRendezVous = genererIdRendezVous();

        RendezVous rendezVous = new RendezVous(idRendezVous, premierJoueur);

        rendezVousDansOrdre.add(rendezVous);
    }

    private String genererIdRendezVous() {
        String idRendezVous = String.valueOf(prochainIdRendezVous);
        prochainIdRendezVous++;

        if (region != null) {
            idRendezVous = region.name() + "¤" + idRendezVous;
        }

        return idRendezVous;
    }

    private int indiceRendezVousParId(String idRendezVous) {
        int indice = -1;

        for (int i = 0; i < rendezVousDansOrdre.size(); i++) {
            if (rendezVousDansOrdre.get(i).siIdEst(idRendezVous)) {
                indice = i;
                break;
            }
        }

        return indice;
    }

    public RendezVousComplet creerRendezVousComplet(String idRendezVous, Joueur deuxiemeJoueur) {

        int indiceRendezVous = indiceRendezVousParId(idRendezVous);
        RendezVous rendezVous = rendezVousDansOrdre.get(indiceRendezVous);

        RendezVousComplet rendezVousComplet = null;

        if (rendezVous != null) {

            rendezVousComplet = rendezVous.creerRendezVousComplet(deuxiemeJoueur);

            rendezVousDansOrdre.set(indiceRendezVous, rendezVousComplet);

        }

        return rendezVousComplet;
    }

    public void retirerRendezVous(String idRendezVous) {
        int indiceRendezVous = indiceRendezVousParId(idRendezVous);

        if (indiceRendezVous >= 0) {
            rendezVousDansOrdre.remove(indiceRendezVous);
        }
    }

    private RendezVous rendezVousParId(String idRendezVous) {
        RendezVous rendezVous = null;

        for (RendezVous candidat : rendezVousDansOrdre) {
            if (candidat.siIdEst(idRendezVous)) {
                rendezVous = candidat;
                break;
            }
        }

        return rendezVous;
    }

    public void ajouterScoreAuRendezVous(String idRendezVous,
            Map<Position, Integer> scoreParPosition) {

        RendezVous rendezVous = rendezVousParId(idRendezVous);

        if (rendezVous instanceof RendezVousComplet) {

            RendezVousComplet rendezVousComplet = (RendezVousComplet) rendezVous;

            rendezVousComplet.ajouterScore(scoreParPosition);
        }
    }
}
