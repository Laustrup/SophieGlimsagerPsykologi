package laustrup.sophieglimsagerpsykologi;

import laustrup.utilities.console.Printer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SophieGlimsagerPsykologiApplication {

    public static void main(String[] args) {
        Program.get_instance().setTestingMode(true);
        SpringApplication.run(SophieGlimsagerPsykologiApplication.class, args);
        Program.get_instance().applicationIsRunning();
        Printer.get_instance().print("Application is now running in " + Program.get_instance().get_state() + " mode");
    }
}
