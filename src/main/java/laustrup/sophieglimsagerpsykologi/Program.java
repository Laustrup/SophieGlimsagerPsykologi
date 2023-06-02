package laustrup.sophieglimsagerpsykologi;

import laustrup.sophieglimsagerpsykologi.repositories.DbLibrary;
import lombok.Getter;

/**
 * Contains information about the program and its states.
 */
public class Program {

    /**
     * Singleton instance of the Service.
     */
    private static Program _instance = null;

    /**
     * Checks first if instance is null, otherwise will create a new instance of the object.
     * Created as a lazyfetch.
     * @return The instance of the object, as meant as a singleton.
     */
    public static Program get_instance() {
        if (_instance == null) _instance = new Program();
        return _instance;
    }

    private Program() {}

    /**
     * Will determine if the application has been started.
     * can not be set to false, when it is set to true.
     */
    @Getter
    private boolean _applicationIsRunning = false;

    /**
     * Will set the applicationIsRunning value to true.
     * @return True if the application already was running.
     */
    public boolean applicationIsRunning() {
        boolean alreadyStarted = _applicationIsRunning;
        _applicationIsRunning = true;

        return alreadyStarted;
    }

    /** A current state of the program.
     * If it's testing, connection will be in memory H2 db for testing purpose,
     * otherwise it will run with the connection that is set.
     */
    @Getter
    private static State _state = State.PRODUCTION;

    /**
     * Will set the testing mode state, but only if the application hasn't started yet.
     * @param isTesting True if it should be in testing mode.
     * @return The previously state of testing mode.
     */
    public boolean setTestingMode(boolean isTesting) {
        boolean alreadyInTestingMode = _state.equals(State.TESTING);
        if (isTesting)
            _state = State.TESTING;
        if (isTesting != alreadyInTestingMode)
            DbLibrary.get_instance();

        return alreadyInTestingMode;
    }

    /** A state of the program. */
    public enum State {
        TESTING,
        PRODUCTION
    }
}
