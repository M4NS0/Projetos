package br.edu.pucgoias.linhasdeonibus.servlet.motorista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.pucgoias.sistemaestoque.controle.MotoristaControle;
import br.edu.pucgoias.sistemaestoque.modelo.Motorista;

/**
 * Classe servlet responsavel por receber dados do front-end e fazer o salvamento
 * de um motorista.
 * 
 * @author Jo�o Victor
 * @data 01/12/2020
 */

@WebServlet("/servletsalvardadosmotorista")
public class ServletSalvarDadosMotorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public ServletSalvarDadosMotorista() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		gson = new GsonBuilder().setPrettyPrinting().create();

		Motorista motorista = null;

		String idmotorista = request.getParameter("idmotorista");
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		int id = Integer.parseInt(idmotorista);

		if (idmotorista == null || idmotorista.length() == 0 || idmotorista.isEmpty())
			id = 0;
		else
			id = Integer.parseInt(idmotorista);

		motorista = new Motorista();
		MotoristaControle mc = new MotoristaControle();
		motorista.setIdMotorista(id);
		motorista.setNome(nome);
		motorista.setCpf(cpf);
		motorista = mc.salvarComMsg(motorista);

		String retornoJsonString = "";
		retornoJsonString = this.gson.toJson(motorista);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(retornoJsonString);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}