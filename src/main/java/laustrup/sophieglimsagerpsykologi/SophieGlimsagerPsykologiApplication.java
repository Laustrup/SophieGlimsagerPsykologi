package laustrup.sophieglimsagerpsykologi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SophieGlimsagerPsykologiApplication {

    public static void main(String[] args) {
        Program.get_instance().setTestingMode(true);
        Program.get_instance().applicationIsRunning();
        SpringApplication.run(SophieGlimsagerPsykologiApplication.class, args);
    }
}
