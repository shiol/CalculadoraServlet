import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Operacoes")
public class Operacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Operacoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String t = request.getParameter("tipo") != "" ? request.getParameter("tipo") : "1";
		String x = request.getParameter("n1") != "" ? request.getParameter("n1") : "0";
		String y = request.getParameter("n2") != "" ? request.getParameter("n2") : "0";
		
		int tipo = Integer.parseInt(t);
		String option = request.getParameter("option");
		String operacao = request.getParameter("operacao");
		double n1 = Double.parseDouble(x);
		double n2 = Double.parseDouble(y);

		double resultado = submit(tipo, option, operacao, n1, n2);

		request.setAttribute("sn1", n1);
		request.setAttribute("sn2", n2);
		request.setAttribute("sresultado", resultado);
		request.setAttribute("stipo", tipo);

		FileInputStream inputStream = new FileInputStream(new File(getServletContext().getRealPath("") + "/index.html"));
		InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			int c;
			while ((c = streamReader.read()) != -1) {
				response.getWriter().write(c);
			}
		} finally {
			if (streamReader != null) {
				streamReader.close();
				inputStream.close();
			}
			response.getWriter();
		}

		response.getWriter()
			.append("<script>document.getElementById(\"result\").value = \"" + resultado + "\";</script>")
			.append("<script>document.getElementById(\"n1\").value = \"" + n1 + "\";</script>")
			.append("<script>document.getElementById(\"n2\").value = \"" + n2 + "\";</script>")
			.append("<script>document.getElementById(\"tipo\").value = \"" + tipo + "\";</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public double fatorial(double num) {
		if (num < 0) {
			return -1;
		} else if (num == 0) {
			return 1;
		} else {
			return (num * fatorial(num - 1));
		}
	}

	public double submit(int tipo, String option, String operacao, double n1, double n2) {
		String op;
		double valorFinal = 0;

		if (tipo == 2)
			op = option;
		else
			op = operacao;

		switch (op) {
		case "fat":
			valorFinal = fatorial(n1);
			break;
		case "sqrt":
			valorFinal = Math.sqrt(n1);
			break;
		case "sqr":
			valorFinal = Math.pow(n1, 2);
			break;
		case "cub":
			valorFinal = Math.pow(n1, 3);
			break;
		case "sin":
			valorFinal = Math.sin(n1);
			break;
		case "inv":
			valorFinal = 1 / n1;
			break;
		case "sum":
			valorFinal = n1 + n2;
			break;
		case "minus":
			valorFinal = n1 - n2;
			break;
		case "multi":
			valorFinal = n1 * n2;
			break;
		case "divide":
			valorFinal = n1 / n2;
			break;
		case "mod":
			valorFinal = n1 % n2;
			break;
		// case "real_dolar":
		// valorFinal = n1 * n3 * n4;
		// break;
		// case "dolar_real":
		// valorFinal = n1 * n3 * n4;
		// break;
		// case "real_euro":
		// valorFinal = n1 * n3 * n4;
		// break;
		// case "euro_real":
		// valorFinal = n1 * n3 * n4;
		// break;
		// case "dec_bin":
		// valorFinal = parseInt(n1).toString(2);
		// break;
		// case "dec_hex":
		// valorFinal = parseInt(n1).toString(16);
		// break;
		// case "bin_dec":
		// valorFinal = parseInt(n1, 2);
		// break;
		// case "hex_dec":
		// valorFinal = parseInt(n1, 16);
		// break;
		default:
			break;
		}
		return valorFinal;
	}
}
