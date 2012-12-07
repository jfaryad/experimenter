package org.experimenter.web.component;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.util.time.Duration;

public class AjaxTimerBehavior extends AbstractAjaxTimerBehavior {
    private static final long serialVersionUID = -3979519326924418600L;

    private final Component componentToRefresh;

    public AjaxTimerBehavior(Component componentToRefresh, int refreshIntervalInSeconds) {
        super(Duration.seconds(refreshIntervalInSeconds));
        this.componentToRefresh = componentToRefresh;
    }

    @Override
    protected void onTimer(final AjaxRequestTarget target) {
        target.add(componentToRefresh);
    }

    @Override
    protected String findIndicatorId() {
        return null;
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new AjaxCallListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public CharSequence getBeforeHandler(Component component) {
                return "ajaxHourglassDisableForNextRequest = true; ";
            }
        });
    }
}