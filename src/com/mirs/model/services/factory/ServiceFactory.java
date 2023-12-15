package com.mirs.model.services.factory;

import com.mirs.model.domain.Instrument;
import com.mirs.model.services.IService;
import com.mirs.model.services.branch.BranchServiceImpl;
import com.mirs.model.services.branch.IBranchService;
import com.mirs.model.services.instrument.IInstrumentService;
import com.mirs.model.services.instrument.InstrumentServiceImpl;
import com.mirs.model.services.login.ILoginService;
import com.mirs.model.services.login.LoginServiceImpl;

import com.mirs.model.business.exception.ServiceLoadException;

import java.util.HashMap;
import java.util.Map;

/**
 * Simplistic implementation of a Service Factory.
 * See  com.mirs.model.business.factory.ServiceFactory for a refined version
 *
 * @author Nathan.Gulley
 *
 */
public class ServiceFactory {

    // Next three lines part of the Singleton Pattern.
    // Read article on Singleton Pattern issues:
    // http://www.ibm.com/developerworks/java/library/j-dcl.html
    private ServiceFactory() { }

    private static ServiceFactory serviceFactory = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    /**
     * Service instance persistence mechanism needed to maintain application state when no database is used
     */
    private Map<String, IService> serviceList = new HashMap<String, IService>();

    /**
     *
     * @param serviceName
     * @return
     * @throws ServiceLoadException
     */
    public IService getService(String serviceName) throws ServiceLoadException {
        if (serviceList.containsKey(serviceName)) {
            return serviceList.get(serviceName);
        }
        try {
            // Class is a parametrizable class. By writing Class<?>, we're declaring a Class object
            // which can be of any type (? is a wildcard).
            // Reference about Generics and Wildcards:http://docs.oracle.com/javase/tutorial/java/generics/wildcards.html
            Class<?> c = Class.forName(getImplName(serviceName));
            IService iService = (IService)c.newInstance();
            serviceList.put(serviceName, iService);
            return iService;
        } catch (Exception excp) {
            serviceName = serviceName + " not loaded";
            throw new ServiceLoadException(serviceName, excp);
        }
    }

    /**
     *
     * @param serviceName
     * @return
     * @throws Exception
     */
    private String getImplName(String serviceName) throws Exception {

        java.util.Properties props = new java.util.Properties();

        // TODO: Hard coding path of the file is restrictive because if the file
        // is renamed or
        // moved, one has to remember to update it here.
        //
        // More appropriate approach is to pass this as a System property (-D
        // option)
        // at application startup time.
        //
        // Student Exercise: Research and all -D option in your HW
        //
        // Hint(actually more like a solution) :
        //
        // If running in Eclipse
        // =====================
        //
        // Right click on the unit test and navigate to
        // 1. Run As -> Run Configuration
        // 2. Select Arguments Tab
        // 3. In VM Arguments section, add the -D property
        // -Dprop_location=<drive><location>\application.properties
        // Example:
        // -Dprop_location=E:\FleetRental\config\application.properties
        //
        // If running on command line
        // ==========================
        // You'd pass in the above -D option with the java command.

        /*
         * java.io.FileInputStream fis = new java.io.FileInputStream(
         * "C:\\EDrive\\Regis\\MSCS670\\NewCourse\\NewCourseSoftwareInstall\\ganymede\\eclipse_workspace\\FleetRental_Week4\\config\\application.properties"
         * );
         */

        String propertyFileLocation = System.getProperty("prop_location");

        // System.out.println("Property File Location passed : " + propertyFileLocation);
        java.io.FileInputStream fis = new java.io.FileInputStream(
                propertyFileLocation);

        props.load(fis);
        fis.close();
        return props.getProperty(serviceName);
    }

    public IBranchService getBranchService() { return new BranchServiceImpl(); }

    public IInstrumentService getInstrumentService() {
        return new InstrumentServiceImpl();
    }

    public ILoginService getLoginService() {
        return new LoginServiceImpl();
    }


}