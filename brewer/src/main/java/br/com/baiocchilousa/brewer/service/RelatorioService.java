package br.com.baiocchilousa.brewer.service;

import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.brewer.dto.PeriodoRelatorio;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 * Classe de serviço para impressão do relatório utilizando a API do JasperReports diretamente
 * @author leolo
 *
 */
@Service
public class RelatorioService {

	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) throws Exception {
		
		// Criamos os parâmetros a serem enviados ao relatório
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		
		//Criamos uma mapa para os parâmetros do relatório
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");//Formato de saída do relatório
		parametros.put("data_inicio", dataInicio);//Parâmetro 'data_inicio' dentro do relatório
		parametros.put("data_fim", dataFim);//Parâmetro 'data_fim' dentro do relatório
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/relatorio_vendas_emitidas.jasper");
		
		Connection conn = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conn);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			conn.close();
		}
		
	}
}
