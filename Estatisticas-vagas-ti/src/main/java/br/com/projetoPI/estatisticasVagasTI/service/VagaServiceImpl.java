package br.com.projetoPI.estatisticasVagasTI.service;

import java.time.LocalDate;
import java.util.*;
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
import enumerate.Linguagem;

@Service
public class VagaServiceImpl implements VagaService {
	
	@Autowired 
    private JavaMailSender mailSender;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	public int quantidadeVagas;
	private List<Vaga> vagasParaSalvar = new ArrayList<Vaga>();
	private List<Vaga> vagas = new ArrayList<Vaga>();
	private List<String> urlVagas;
	private List<String> urlsDaBase;
	
	
	public ResponseEntity<Iterable<Vaga>> buscarVagasDaBase() {
		
		return new ResponseEntity<Iterable<Vaga>>(vagaRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Iterable<Vaga>> buscarVagas() {

		Arrays.asList(Linguagem.values()).stream().forEach(linguagem->{
			urlVagas = buscaUrlsVagas(linguagem.url());
			vagas = preencheAtributosVagas(urlVagas, linguagem);
			urlVagas = vagas.stream().filter(distinctByKey(v -> v.getUrl())).map(Vaga::getUrl).collect(Collectors.toList());
			urlsDaBase = vagaRepository.obterVagasPorUrls(urlVagas,linguagem).stream().map(Vaga::getUrl).collect(Collectors.toList());
			vagasParaSalvar.addAll(vagas.stream().filter(v -> !urlsDaBase.contains(v.getUrl())).collect(Collectors.toList()));
			
		});
		
		return new ResponseEntity<Iterable<Vaga>>(vagaRepository.saveAll(vagasParaSalvar), HttpStatus.OK);
	}

	private List<String> buscaUrlsVagas(String url) {
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
	
	private List<Vaga> preencheAtributosVagas(List<String> urls, Linguagem linguagem) {
		List<Vaga> vagas = new ArrayList<>();
		
		for(String url:urls) {
			Document urlDaPaginaVaga = getHtml(url);
			String descricaoCargo = urlDaPaginaVaga.getElementsByClass("topcard__title").first().text();
			String detalhes = urlDaPaginaVaga.getElementsByClass("description__text").first().text();
			Elements links = urlDaPaginaVaga.getElementsByClass("topcard__org-name-link");
			String empresa = urlDaPaginaVaga.title().substring(0, getHtml(url).title().indexOf("está contrat")).trim();
			for(Element link:links) {
				if(link.absUrl("href").contains("company"))
					empresa = link.text();
			}
			
			Vaga novaVaga = new Vaga(descricaoCargo, empresa, detalhes, url, LocalDate.now(),linguagem);
			vagas.add(novaVaga);
		}
		validarVagas(vagas);
		return vagas;
	}

	private <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}
	
	private String sendMail(List<Vaga> vagas) {
        SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("API Vagas - Email de Validação");
		message.setText("Algum atributo das vagas com url " + vagas.stream()
																.map(v -> v.getUrl())
																.collect(Collectors.toList())
																.toString()
																.replace("[", "")
																.replace("]", "") + " foram salvos nulos.");
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
	
	private void validarVagas(List<Vaga> vagas) {
		List<Vaga> vagasInvalidadas = new ArrayList<>();
		for(Vaga vaga : vagas) {
			if(isEmptyOrNull(vaga.getCargo()) || isEmptyOrNull(vaga.getEmpresa())
			|| isEmptyOrNull(vaga.getData().toString()) || vaga.getLinguagem() != null) {
				vagasInvalidadas.add(vaga);
			}
		}

		if(!vagasInvalidadas.isEmpty())
			sendMail(vagasInvalidadas);
	}
	
	private boolean isEmptyOrNull(String parametro) {
		return parametro.isEmpty() || parametro ==null;
	}
}
