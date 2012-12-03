package org.experimenter.web.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.IResourceStream;

public abstract class AjaxDownload extends AbstractAjaxBehavior {
    private static final long serialVersionUID = -5987676238121182091L;

    /**
     * Call this method to initiate the download.
     */
    public void initiate(AjaxRequestTarget target)
    {
        CharSequence url = getCallbackUrl();
        target.appendJavaScript("window.location.href='" + url + "'");
    }

    @Override
    public void onRequest()
    {
        getComponent().getRequestCycle().scheduleRequestHandlerAfterCurrent(
                new ResourceStreamRequestHandler(getResourceStream(), getFileName()) {
                    @Override
                    public void respond(IRequestCycle requestCycle) {
                        super.respond(requestCycle);
                        onAfterDownload();
                    }
                });
    }

    /**
     * @see ResourceStreamRequestTarget#getFileName()
     */
    public abstract String getFileName();

    /**
     * Hook method providing the actual resource stream.
     */
    protected abstract IResourceStream getResourceStream();

    /**
     * Callback for custom action after download has finished.
     */
    protected void onAfterDownload() {

    }
}
