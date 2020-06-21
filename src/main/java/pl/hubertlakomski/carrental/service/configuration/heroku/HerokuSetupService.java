package pl.hubertlakomski.carrental.service.configuration.heroku;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("heroku")
@Service
@RequiredArgsConstructor
public class HerokuSetupService implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {



    }
}
