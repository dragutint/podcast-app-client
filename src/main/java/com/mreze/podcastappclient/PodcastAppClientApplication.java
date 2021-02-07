package com.mreze.podcastappclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

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
		SpringApplication.run(PodcastAppClientApplication.class,args);
//		Application.launch(PodcastApplication.class, args);
	}

	public static AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInBits,
				channels, signed, bigEndian);
	}


	@Override
	public void run(String... args) throws Exception {
		AudioFormat format = getAudioFormat();
		SourceDataLine speakers;

		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		speakers.open(format);
		speakers.start();
		String hostname = "localhost";
		int port = 5555;

		try {
			InetAddress address = InetAddress.getByName(hostname);
			DatagramSocket socket = new DatagramSocket();



			while (true) {
				byte[] bufferRequest = new byte[512];
				DatagramPacket request = new DatagramPacket(bufferRequest, bufferRequest.length, address, port);
				socket.send(request);


				byte[] buffer = new byte[1024];
				DatagramPacket response = new DatagramPacket(buffer, buffer.length);
				socket.receive(response);

				speakers.write(response.getData(), 0, response.getData().length);
			}

		} catch (SocketTimeoutException ex) {
			System.out.println("Timeout error: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Client error: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}
