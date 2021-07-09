package br.com.projeto_pi.estatisticas_vagas_ti.enumerate;

public enum Linguagem {
	
	JAVA("https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=Java&location=Fortaleza%2C%2BCear%C3%A1%2C%2BBrasil&originalSubdomain=br"),
	PYTHON("https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=Python&location=Fortaleza%2C%2BCear%C3%A1%2C%2BBrasil&originalSubdomain=br"),
	PHP("https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=php&location=Fortaleza%2C%20Cear%C3%A1%2C%20Brasil&originalSubdomain=br"),
	JAVASCRIPT("https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=Javascript&location=Fortaleza%2C%2BCear%C3%A1%2C%2BBrasil&originalSubdomain=br"),
	CSHARP("https://www.linkedin.com/jobs/search/?distance=0&f_TP=1&f_TPR=r86400&geoId=103836099&keywords=C%23&location=Fortaleza%2C%20Cear%C3%A1%2C%20Brasil"),
	C("https://www.linkedin.com/jobs/search/?distance=0&f_TP=1&f_TPR=r86400&geoId=103836099&keywords=C&location=Fortaleza%2C%20Cear%C3%A1%2C%20Brasil"),
	DELPHI("https://www.linkedin.com/jobs/search/?distance=0&f_TP=1&f_TPR=r86400&geoId=103836099&keywords=delphi&location=Fortaleza%2C%20Cear%C3%A1%2C%20Brasil"),
	CPLUSPLUS("https://www.linkedin.com/jobs/search/?distance=0&f_TP=1&f_TPR=r86400&geoId=103836099&keywords=c%2B%2B&location=Fortaleza%2C%20Cear%C3%A1%2C%20Brasil"),
	RUBY("https://www.linkedin.com/jobs/search/?f_TP=1&f_TPR=r86400&geoId=103836099&keywords=Ruby&location=Fortaleza%2C%2BCear%C3%A1%2C%2BBrasil&originalSubdomain=br");
	
	private String url;
	
	Linguagem(String url){
		this.url = url;
	}
	
	public String url() {
		return this.url;
	}
}
