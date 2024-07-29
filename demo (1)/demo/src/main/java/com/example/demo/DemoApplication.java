package com.example.demo;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		String jumpServerHost = "i11e108.p.ssafy.io";
		String jumpServerUser = "ubuntu";
		String jumpServerIdentityPath = "/mnt/c/Users/SSAFY/Desktop";

		String remoteServerHost = "192.168.30.188";
		String remoteServerUser = "DESKTOP-MMCQPV1";
		String remoteServerPassword = "";

		StringBuilder requestBuilder = new StringBuilder();

		requestBuilder.append("ssh -i I11E108T.pem ubuntu@i11e108.p.ssafy.io bash \n").append("echo \"hi\" > test.txt");

		String request = requestBuilder.toString();

		StringBuilder response = new StringBuilder();

		Session jumpSession = null;
		Session remoteSession = null;
		ChannelExec channel = null;


		// JSch 객체 생성
		JSch jsch = new JSch();

		// First SSH session: application server -> jump server
		jumpSession = jsch.getSession(jumpServerUser, jumpServerHost, 22);


		// Private Key 설정
		jsch.addIdentity(jumpServerIdentityPath);

		// SSH에 처음 연결할 때, 리모트 호스트의 공개키가 자신의 known_hosts 파일에 저장된 것과 일치하는 지 검사하고 저장할 지 확인
		// no로 설정하면 보안에 취약해지므로 신뢰할 수 없는 네트워크에서는 사용을 지양해야함.
		jumpSession.setConfig("StrictHostKeyChecking", "no");

		// SSH 연결
		jumpSession.connect();


		log.info("SSH session1 connected");
		출처: https://annajin.tistory.com/227 [내일 한걸음 더:티스토리]
	}
}
