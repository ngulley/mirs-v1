package com.mirs.model.business.manager;

import com.mirs.model.business.exception.*;
import com.mirs.model.domain.*;
import com.mirs.model.services.branch.IBranchService;
import com.mirs.model.services.factory.ServiceFactory;
import com.mirs.model.services.exception.*;
import com.mirs.model.services.instrument.*;
import com.mirs.model.services.login.ILoginService;

import java.util.List;


public class InstrumentRentalManager extends AbstractManager{

    private static InstrumentRentalManager myInstance;

    /**
     * keep the constructor private to prevent instantiation by outside callers.
     */
    private InstrumentRentalManager() { }

    /**
     * Assures that there is only one InstrumentRentalManager created.
     * @return InstrumentRentalManager instance
     */
    public static synchronized InstrumentRentalManager getInstance() {
        if (myInstance == null) {
            myInstance = new InstrumentRentalManager();
        }
        return myInstance;
    }

    /**
     * Generic method that all clients of this class can call to perform certain actions.
     *
     * @param commandString
     *            Holds the service operation to be invoked *
     * @param composite
     *            Holds application specific domain state
     * @return false if action failed or true if action is successful
     */
    @Override
    public boolean performAction(String commandString, Composite composite) {
        boolean action = false;

        switch (commandString) {
            case "LOGIN_USER":
                action = loginUser(commandString, composite);
                break;
            case "LOGOUT_USER":
                action = logoutUser(commandString, composite);
                break;
            case "LIST_BRANCHES":
                action = listBranches(commandString, composite);
                break;
            case "LIST_INSTRUMENTS":
                action = listInstruments(commandString, composite);
                break;
            case "ADD_BRANCH":
                action = addBranch(commandString, composite);
                break;
            case "ADD_INSTRUMENT":
                action = addInstrument(commandString, composite);
                break;
            case "UPDATE_BRANCH":
                action = updateBranch(commandString, composite);
                break;
            case "UPDATE_INSTRUMENT":
                action = updateInstrument(commandString, composite);
                break;
            case "DELETE_BRANCH":
                action = deleteBranch(commandString, composite);
                break;
            case "DELETE_INSTRUMENT":
                action = deleteInstrument(commandString, composite);
                break;
            default:
                System.out.println("INFO:  Add new workflows here using here using if/else.");
                break;
        }

        return action;
    }

    private boolean loginUser(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ILoginService loginService;
        boolean isLoggedIn = false;

        try {
            loginService = (ILoginService) serviceFactory.getService(commandString);
            isLoggedIn = loginService.authenticateUser(composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load LoginService." + sle.getMessage());
        } catch (LoginException le) {
            System.out.println("ERROR: InstrumentRentalManager::loginUser() failed." + le.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }

        return isLoggedIn;
    }

    private boolean logoutUser(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ILoginService loginService;
        boolean isLoggedOut = false;

        try {
            loginService = (ILoginService) serviceFactory.getService(commandString);
            isLoggedOut = loginService.logoutUser(composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load LoginService." + sle.getMessage());
        } catch (LoginException le) {
            System.out.println("ERROR: InstrumentRentalManager::logoutUser() failed." + le.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }

        return isLoggedOut;
    }

    private boolean listInstruments(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IInstrumentService instrumentService;
        boolean isListed = false;

        try {
            instrumentService = (IInstrumentService) serviceFactory.getService(commandString);
            isListed = instrumentService.list(composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load InstrumentService." + sle.getMessage());
        } catch (InstrumentException ie) {
            System.out.println("ERROR: InstrumentRentalManager::listInstruments() failed." + ie.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isListed;
    }

    private boolean addInstrument(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IInstrumentService instrumentService;
        boolean isAdded = false;

        try {
            instrumentService = (IInstrumentService) serviceFactory.getService(commandString);
            isAdded = instrumentService.add(composite.getInstrument(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load InstrumentService." + sle.getMessage());
        } catch (InstrumentException ie) {
            System.out.println("ERROR:  InstrumentRentalManager::addInstrument() failed. " + ie.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isAdded;
    }

    private boolean updateInstrument(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IInstrumentService instrumentService;
        boolean isUpdated = false;

        try {
            instrumentService = (IInstrumentService) serviceFactory.getService(commandString);
            isUpdated = instrumentService.update(composite.getInstrument(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load InstrumentService." + sle.getMessage());
        } catch (InstrumentException ie) {
            System.out.println("ERROR:  InstrumentRentalManager::updateInstrument() failed. " + ie.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isUpdated;
    }

    private boolean deleteInstrument(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IInstrumentService instrumentService;
        boolean isDeleted = false;

        try {
            instrumentService = (IInstrumentService) serviceFactory.getService(commandString);
            isDeleted = instrumentService.delete(composite.getInstrument().getId(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load InstrumentService." + sle.getMessage());
        } catch (InstrumentException ie) {
            System.out.println("ERROR:  InstrumentRentalManager::deleteInstrument() failed. " + ie.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isDeleted;
    }

    private boolean listBranches(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IBranchService branchService;
        boolean isListed = false;

        try {
            branchService = (IBranchService) serviceFactory.getService(commandString);
            isListed = branchService.list(composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load BranchService." + sle.getMessage());
        } catch (BranchException be) {
            System.out.println("ERROR: InstrumentRentalManager::listBranches() failed." + be.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }

        return isListed;
    }

    private boolean addBranch(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IBranchService branchService;
        boolean isAdded = false;

        try {
            branchService = (IBranchService) serviceFactory.getService(commandString);
            isAdded = branchService.add(composite.getBranch(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load BranchService." + sle.getMessage());
        } catch (BranchException be) {
            System.out.println("ERROR: InstrumentRentalManager::addBranch() failed." + be.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isAdded;
    }

    private boolean updateBranch(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IBranchService branchService;
        boolean isUpdated = false;

        try {
            branchService = (IBranchService) serviceFactory.getService(commandString);
            isUpdated = branchService.update(composite.getBranch(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load BranchService." + sle.getMessage());
        } catch (BranchException be) {
            System.out.println("ERROR: InstrumentRentalManager::updateBranch() failed." + be.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isUpdated;
    }

    private boolean deleteBranch(String commandString, Composite composite) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IBranchService branchService;
        boolean isDeleted = false;

        try {
            branchService = (IBranchService) serviceFactory.getService(commandString);
            isDeleted = branchService.delete(composite.getBranch().getId(), composite);
        } catch (ServiceLoadException sle) {
            System.out.println("ERROR: InstrumentRentalManager::failed to load BranchService." + sle.getMessage());
        } catch (BranchException be) {
            System.out.println("ERROR: InstrumentRentalManager::deleteBranch() failed." + be.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: InstrumentRentalManager::Unknown error." + e.getMessage());
        }
        return isDeleted;
    }
}
