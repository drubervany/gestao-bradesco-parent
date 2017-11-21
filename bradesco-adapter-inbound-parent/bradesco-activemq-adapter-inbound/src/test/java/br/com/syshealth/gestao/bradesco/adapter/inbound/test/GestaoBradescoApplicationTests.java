package br.com.syshealth.gestao.bradesco.adapter.inbound.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.syshealth.gestao.bradesco.inbound.application.GestaoBradescoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoBradescoApplication.class)
public class GestaoBradescoApplicationTests {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	private Queue queueCadastro = new ActiveMQQueue("bradesco-cadastro");

	@Test
	public void sendCadastro() throws InterruptedException {

		File file = new File("C:\\Users\\DRUBERVANY\\Downloads\\PC12073169\\PC02073169.TXT");

		this.jmsMessagingTemplate.convertAndSend(this.queueCadastro, getBytes(file));
		
		Thread.sleep(30000);
	}

	public byte[] getBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);
		} catch (FileNotFoundException fnfex) {
		} catch (IOException ioex) {
		}
		return sendBuf;
	}

}
