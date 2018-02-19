package br.com.baiocchilousa.brewer.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.baiocchilousa.brewer.model.Cerveja;
import br.com.baiocchilousa.brewer.model.ItemVenda;
import br.com.baiocchilousa.brewer.model.Venda;
import br.com.baiocchilousa.brewer.storage.FotoStorage;

@Component
public class Mailer {

	private static Logger LOGGER = LoggerFactory.getLogger(Mailer.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async//Método assíncrono
	public void enviar(Venda venda) {
		
		//Mensagem simples
		/*SimpleMailMessage mensagem = new SimpleMailMessage();
		mensagem.setFrom("leolousa@hotmail.com");
		mensagem.setTo(venda.getCliente().getEmail());
		mensagem.setSubject("Brewer! - Venda efetuada!");
		mensagem.setText("Obrigado por comprar no Brewer!");
		
		mailSender.send(mensagem);*/
	
		///////////////////////////////////////////////////////////
		
		//Mensagem com template HTML com Thymeleaf
		Context context = new Context(new Locale("pt","BR"));
		context.setVariable("venda", venda);
		context.setVariable("logo", "logo");
		
		
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockCerveja = false;
		
		for(ItemVenda item : venda.getItens()) {
			Cerveja cerveja = item.getCerveja();
			if(cerveja.temFoto()) {
				String cid = "foto-" + cerveja.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, cerveja.getFoto() + "|" + cerveja.getContentType());
			} else {
				adicionarMockCerveja = true;
				context.setVariable("mockCerveja", "mockCerveja");
			}
		}
		
		String email = thymeleaf.process("mail/resumo-venda", context);
		
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("leolousa@hotmail.com");
			helper.setTo(venda.getCliente().getEmail());
			helper.setSubject(String.format("Brewer - Venda n. %d", venda.getCodigo()));
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo-gray.png"));
			
			if(adicionarMockCerveja) {
				helper.addInline("mockCerveja", new ClassPathResource("static/images/cerveja-mock.png"));	
			}
			
			//Formação do array com as fotos
			for(String cid : fotos.keySet()){
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				helper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);
			}
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			
			LOGGER.error("Erro enviando e-mail!", e);
			
		}
	
	
	
	
	}
}
