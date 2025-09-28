package pong.dorsal.taches;

import static ca.ntro.app.tasks.backend.BackendTasks.*;
import static ca.ntro.app.tasks.frontend.FrontendTasks.message;

import ca.ntro.app.tasks.backend.BackendTasks;
import pong.commun.messages.MsgAjouterRendezVous;
import pong.commun.messages.MsgAjouterScoreAuRendezVous;
import pong.commun.messages.MsgInitialiserFileAttente;
import pong.commun.messages.MsgRejoindreRendezVous;
import pong.commun.messages.MsgRetirerRendezVous;
import pong.commun.modeles.ModeleFileAttente;
import pong.commun.valeurs.RendezVousComplet;

public class ModifierFileAttente {

    public static void creerTaches(BackendTasks tasks) {

        tasks.taskGroup("ModifierFileAttente")

                .waitsFor(model(ModeleFileAttente.class))

                .contains(subTasks -> {

                    initialiser(subTasks);

                    ajouerRendezVous(subTasks);

                    retirerRendezVous(subTasks);

                    rejoindreRendezVous(subTasks);

                    envoyerMsgCreerPartie(subTasks);

                    ajouterScoreAuRendezVous(subTasks);

                });
    }

    private static void initialiser(BackendTasks subTasks) {
        subTasks.task("initialiser")

                .waitsFor(model(ModeleFileAttente.class))

                .waitsFor(message(MsgInitialiserFileAttente.class))

                .executes(inputs -> {

                    MsgInitialiserFileAttente msgInitialiserFileAttente = inputs
                            .get(message(MsgInitialiserFileAttente.class));
                    ModeleFileAttente fileAttente = inputs.get(model(ModeleFileAttente.class));

                    msgInitialiserFileAttente.appliquerA(fileAttente);
                });
    }

    private static void rejoindreRendezVous(BackendTasks subTasks) {
        subTasks.task("rejoindreRendezVous")

                .waitsFor(model(ModeleFileAttente.class))

                .waitsFor(message(MsgRejoindreRendezVous.class))

                .executesAndReturnsValue(inputs -> {

                    MsgRejoindreRendezVous msgRejoindreRendezVous = inputs.get(message(MsgRejoindreRendezVous.class));
                    ModeleFileAttente fileAttente = inputs.get(model(ModeleFileAttente.class));

                    RendezVousComplet rendezVousComplet = msgRejoindreRendezVous.appliquerA(fileAttente);

                    return rendezVousComplet;

                });
    }

    private static void envoyerMsgCreerPartie(BackendTasks subTasks) {
        subTasks.task("envoyerMsgCreerPartie")

                .waitsFor("rejoindreRendezVous")

                .executes(inputs -> {

                    RendezVousComplet rendezVousComplet = inputs.get("rejoindreRendezVous");

                    if (rendezVousComplet != null) {

                        rendezVousComplet.envoyerMsgCreerPartie();

                    }
                });
    }

    private static void retirerRendezVous(BackendTasks subTasks) {
        subTasks.task("retirerRendezVous")

                .waitsFor(model(ModeleFileAttente.class))

                .waitsFor(message(MsgRetirerRendezVous.class))

                .executes(inputs -> {

                    MsgRetirerRendezVous msgRetirerRendezVous = inputs.get(message(MsgRetirerRendezVous.class));
                    ModeleFileAttente fileAttente = inputs.get(model(ModeleFileAttente.class));

                    msgRetirerRendezVous.retirerDe(fileAttente);
                });

    }

    private static void ajouerRendezVous(BackendTasks subTasks) {
        subTasks.task("ajouterRendezVous")

                .waitsFor(model(ModeleFileAttente.class))

                .waitsFor(message(MsgAjouterRendezVous.class))

                .executes(inputs -> {

                    MsgAjouterRendezVous msgAjouterRendezVous = inputs.get(message(MsgAjouterRendezVous.class));
                    ModeleFileAttente fileAttente = inputs.get(model(ModeleFileAttente.class));

                    msgAjouterRendezVous.ajouterA(fileAttente);

                });
    }

    private static void ajouterScoreAuRendezVous(BackendTasks subTasks) {
        subTasks.task("ajouterScoreAuRendezVous")
                .waitsFor(model(ModeleFileAttente.class))
                .waitsFor(message(MsgAjouterScoreAuRendezVous.class))
                .executes(inputs -> {

                    MsgAjouterScoreAuRendezVous msgAjouterScoreAuRendezVous = inputs
                            .get(message(MsgAjouterScoreAuRendezVous.class));
                    ModeleFileAttente fileAttente = inputs.get(model(ModeleFileAttente.class));

                    msgAjouterScoreAuRendezVous.ajouterPointA(fileAttente);

                });
    }
}
