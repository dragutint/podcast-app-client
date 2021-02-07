package com.mreze.podcastappclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class PodcastAppClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PodcastAppClientApplication.class,args);
//		Application.launch(PodcastApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String hostname = "localhost";
		int port = 8081;

		InetAddress address = InetAddress.getByName(hostname);
		DatagramSocket socket = new DatagramSocket();

		byte[] buffer = new byte[512];

		DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
		socket.send(request);

		DatagramPacket response = new DatagramPacket(buffer, buffer.length);
		socket.receive(response);

		String quote = new String(buffer, 0, response.getLength());

		System.out.println(quote);
	}
}
