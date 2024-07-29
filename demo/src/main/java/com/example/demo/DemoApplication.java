package com.example.demo;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import com.jcraft.jsch.JSchException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws JSchException, IOException {
		SpringApplication.run(DemoApplication.class, args);
		String jumpServerHost = "i11e108.p.ssafy.io";
		String jumpServerUser = "ubuntu";
		String jumpServerIdentityPath = "C:\\Users\\SSAFY\\Desktop\\I11E108T.pem";

		String remoteServerHost = "localhost";
		String remoteServerUser = "SSAFY";
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


		//채널 하나 만듦
		channel = (ChannelExec) jumpSession.openChannel("exec");

		// 실행할 명령어 설정
		String command = "echo \"hi\" > test.txt";
		channel.setCommand(command);

		// 명령어 실행 시 사용할 스트림 설정
		channel.setInputStream(null);
		channel.setErrStream(System.err);

		// 명령어 실행
		channel.connect();



	}
}