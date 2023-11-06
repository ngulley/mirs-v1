package com.mirs.model.services.factory;

import com.mirs.model.services.instrument.IInstrumentService;
import com.mirs.model.services.instrument.InstrumentServiceImpl;
import com.mirs.model.services.login.ILoginService;
import com.mirs.model.services.login.LoginServiceImpl;

/**
 * Simplistic implementation of a Service Factory.
 * See  com.mirs.model.business.factory.ServiceFactory for a refined version
 *
 * @author Nathan.Gulley
 *
 */
public class ServiceFactory {

    public ILoginService getLoginService() {
        return new LoginServiceImpl();
    }

    public IInstrumentService getInstrumentService() {
        return new InstrumentServiceImpl();
    }
}