package br.dev.brum.poc.jpos;

import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevBrumPocJposApplication {

	public static void main(String[] args) {
		Q2 q2 = new Q2();
		q2.start();
		SpringApplication.run(DevBrumPocJposApplication.class, args);
	}

	@Bean
	public QMUX exposeQmux() throws Exception {
		return (QMUX) NameRegistrar.get("mux.s-mux");
	}

}
