package pl.hubertlakomski.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("heroku")
@Service
@RequiredArgsConstructor
public class SetupService implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
