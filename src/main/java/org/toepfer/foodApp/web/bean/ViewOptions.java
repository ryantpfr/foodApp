package org.toepfer.foodApp.web.bean;

/**
 * Created by toepfer on 8/20/2017.
 */
public class ViewOptions {

    private boolean showLoginForm;

    private boolean showAuthFailMessage;

    public boolean getShowLoginForm() {
        return showLoginForm;
    }

    public void setShowLoginForm(boolean showLoginForm) {
        this.showLoginForm = showLoginForm;
    }

    public boolean getShowAuthFailMessage() {
        return showAuthFailMessage;
    }

    public void setShowAuthFailMessage(boolean showAuthFailMessage) {
        this.showAuthFailMessage = showAuthFailMessage;
    }
}
