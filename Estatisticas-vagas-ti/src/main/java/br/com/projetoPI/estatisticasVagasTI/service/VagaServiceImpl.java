package br.com.projetoPI.estatisticasVagasTI.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.projetoPI.estatisticasVagasTI.entity.Vaga;
import br.com.projetoPI.estatisticasVagasTI.repository.VagaRepository;

@Service
public class VagaServiceImpl implements VagaService {
	
	@Autowired 
    private JavaMailSender mailSender;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	private static final String url = "https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=Java&location=Fortaleza%2C%2BCear%C3%A1%2C%2BBrasil&originalSubdomain=br";	
	public int quantidadeVagas;
	
	
	public ResponseEntity<Iterable<Vaga>> buscarVagas() {
		List<Vaga> vagas;
		List<String> urlVagas;
		List<String> urlsDaBase;
		
		urlVagas = buscaUrlsVagas();
		vagas = preencheAtributosVagas(urlVagas);
		
		urlVagas = vagas.stream().filter(distinctByKey(u -> u.getUrl())).map(Vaga::getUrl).collect(Collectors.toList());
		urlsDaBase = vagaRepository.obterVagasPorUrls(urlVagas).stream().map(Vaga::getUrl).collect(Collectors.toList());
		vagas = vagas.stream().filter(v -> !urlsDaBase.contains(v.getUrl())).collect(Collectors.toList());
		
		return new ResponseEntity<Iterable<Vaga>>(vagaRepository.saveAll(vagas), HttpStatus.OK);
	}

	private List<String> buscaUrlsVagas() {
		List<String> urlVagas = new ArrayList<String>();
		
		Elements vagas = getHtml(url)
				.select("a");
		for(Element vaga:vagas) {
			String link = vaga.attr("abs:href");
			if(link.contains("/jobs/view")) {
				urlVagas.add(link.substring(0, link.indexOf("?")));
				quantidadeVagas+=1;
			}
		}
		
		return urlVagas;
	}
	
	/*
	 * Caputura a estrutura html da pagina que contem as vagas
	 * */
	private Document getHtml(String url) {
		Document html = null;
		try {
			html = Jsoup.connect(url)
					.userAgent("Chrome/87.0.4280.88").timeout(5000).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}
	
	private List<Vaga> preencheAtributosVagas(List<String> urls) {
		List<Vaga> vagas = new ArrayList<>();
		
		for(String url:urls) {
			String descricaoCargo = getHtml(url).getElementsByClass("topcard__title").first().text();
			Elements links = getHtml(url).getElementsByClass("topcard__org-name-link");
			String empresa = getHtml(url).title().substring(0, getHtml(url).title().indexOf("está contrat")).trim();
			for(Element link:links) {
				if(link.absUrl("href").contains("company"))
					empresa = link.text();
			}
			
			Vaga novaVaga = new Vaga(descricaoCargo, empresa, url, LocalDate.now());
			validarVaga(novaVaga);
			vagas.add(novaVaga);
		}
		return vagas;
	}

	private <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}
	
	private String sendMail(Vaga vaga) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Algum atributo da vaga com url "+ vaga.getUrl() +" foi salvo null");
        message.setTo("alexandremendes1005@gmail.com");
        message.setFrom("jdavidjl97@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
	
	private void validarVaga(Vaga vaga) {
		if(isEmptyOrNull(vaga.getcargo()) || isEmptyOrNull(vaga.getEmpresa())
		|| isEmptyOrNull(vaga.getData().toString())) {
			sendMail(vaga);
		}
	}
	
	private boolean isEmptyOrNull(String parametro) {
		return parametro.isEmpty() || parametro ==null;
	}
}
