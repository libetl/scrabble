package org.toilelibre.libe.userinteractions.model.actions;

public class ActionRedirect {
    private String  beanName;
    private boolean closePrevious;
    private String  redirect;

    public ActionRedirect (final String redirect1) {
        super ();
        this.redirect = redirect1;
        this.closePrevious = false;
    }

    public ActionRedirect (final String redirect1, final boolean closePrevious1) {
        super ();
        this.redirect = redirect1;
        this.closePrevious = closePrevious1;
    }

    public ActionRedirect (final String beanName2, final String redirect2) {
        super ();
        this.redirect = redirect2;
        this.closePrevious = false;
        this.beanName = beanName2;
    }

    public ActionRedirect (final String beanName2, final String redirect2,
            final boolean closePrevious2) {
        super ();
        this.redirect = redirect2;
        this.closePrevious = closePrevious2;
        this.beanName = beanName2;
    }

    public final String getBeanName () {
        return this.beanName;
    }

    public final String getRedirect () {
        return this.redirect;
    }

    public final boolean isClosePrevious () {
        return this.closePrevious;
    }

    public final void setBeanName (final String beanName1) {
        this.beanName = beanName1;
    }

    public final void setClosePrevious (final boolean closePrevious1) {
        this.closePrevious = closePrevious1;
    }

    public final void setRedirect (final String redirect1) {
        this.redirect = redirect1;
    }
}
