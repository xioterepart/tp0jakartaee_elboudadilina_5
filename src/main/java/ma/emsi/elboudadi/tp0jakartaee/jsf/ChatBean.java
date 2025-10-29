package ma.emsi.elboudadi.tp0jakartaee.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ChatBean implements Serializable {

    private String roleSysteme = "helpful assistant"; // Valeur par défaut
    private String question = "";
    private String reponse = "";
    private String conversation = "";
    private boolean selectionRoleActive = true;

    // --- Propriétés pour la liste déroulante (Roles) ---

    private final List<String> roles;

    public ChatBean() {
        roles = new ArrayList<>();
        roles.add("helpful assistant");
        roles.add("traducteur français-anglais");
        roles.add("guide touristique");
    }

    public List<String> getRoles() {
        return roles;
    }

    // --- Getters et Setters pour les propriétés d'état ---

    public String getRoleSysteme() {
        return roleSysteme;
    }

    public void setRoleSysteme(String roleSysteme) {
        this.roleSysteme = roleSysteme;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public boolean isSelectionRoleActive() {
        return selectionRoleActive;
    }

    public void setSelectionRoleActive(boolean selectionRoleActive) {
        this.selectionRoleActive = selectionRoleActive;
    }

    // --- Méthodes "Action" ---

    public void envoyerQuestion() {
        // Valider la question avant le traitement
        if (question == null || question.trim().isEmpty()) {
            // Utilise FacesContext pour afficher un message d'erreur
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez entrer une question."));
            reponse = "";
            return;
        }

        // Désactive la sélection du rôle après le premier envoi
        selectionRoleActive = false;

        // --- Début du traitement simple (À REMPLACER pour le bonus) ---
        String traitement = roleSysteme + " : ||" + question.toUpperCase() + "||";
        reponse = traitement;
        // --- Fin du traitement simple ---

        // Mise à jour de l'historique
        conversation += "Utilisateur : " + question + "\n";
        conversation += "API : " + reponse + "\n\n";

        // Nettoyage de la zone de question
        question = "";
    }

    /**
     * Cette méthode action est appelée par le bouton "Nouveau chat".
     * Le fait de retourner "index.xhtml" (qui est la page en cours) indique à JSF
     * que l'utilisateur change de vue (View).
     * Étant donné que le bean est @ViewScoped, une nouvelle instance sera créée,
     * réinitialisant toutes les propriétés du bean.
     * @return "index.xhtml" pour forcer le changement de vue.
     */
    public String nouveauChat() {
        return "index.xhtml";
    }
}