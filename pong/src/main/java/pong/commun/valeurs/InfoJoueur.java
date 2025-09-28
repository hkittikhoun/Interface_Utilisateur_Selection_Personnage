package pong.commun.valeurs;

import java.util.Map;

import ca.ntro.app.models.ModelValue;
import pong.commun.enums.Position;
import pong.frontal.vues.VuePartie;

public class InfoJoueur implements ModelValue {

    private Joueur joueur = null;
    private int score = 0;

    public InfoJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void afficherSur(VuePartie vuePartie, Position position) {
        vuePartie.afficherScore(position, score);
        joueur.afficherSur(vuePartie, position);
    }

    public void incrementerScore() {
        score++;
    }

    public void copierScoreDans(Map<Position, Integer> scoreParPosition, Position position) {
        scoreParPosition.put(position, score);
    }
}