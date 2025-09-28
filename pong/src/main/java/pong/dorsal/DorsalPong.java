package pong.dorsal;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import pong.dorsal.taches.Initialisation;
import pong.dorsal.taches.ModifierFileAttente;
import pong.dorsal.taches.ModifierPartie;

public class DorsalPong extends LocalBackendNtro {

    @Override
    public void createTasks(BackendTasks tasks) {

        Initialisation.creerTaches(tasks);
        ModifierFileAttente.creerTaches(tasks);
        ModifierPartie.creerTaches(tasks);

    }

}
