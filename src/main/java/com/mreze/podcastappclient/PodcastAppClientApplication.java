package com.mreze.podcastappclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import javafx.application.Application;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class PodcastAppClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//SpringApplication.run(PodcastAppClientApplication.class,args);
		Application.launch(PodcastApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {


	}
}
