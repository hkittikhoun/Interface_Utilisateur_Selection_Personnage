package fighting_2d.dorsal;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import fighting_2d.dorsal.taches.Initialisation;
import fighting_2d.dorsal.taches.ModifierCreationProfil;

public class DorsalFighting extends LocalBackendNtro {

    @Override
    public void createTasks(BackendTasks tasks) {
        Initialisation.creerTaches(tasks);
        ModifierCreationProfil.creerTaches(tasks);
    }

}