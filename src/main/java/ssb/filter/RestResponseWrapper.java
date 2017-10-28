package ssb.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;

public class RestResponseWrapper extends HttpServletResponseWrapper {

	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private PrintWriter writer = new PrintWriter(bos);

	public RestResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletResponse getResponse() {
		return this;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new ServletOutputStream() {
			private TeeOutputStream tee = new TeeOutputStream(
					RestResponseWrapper.super.getOutputStream(), bos);

			@Override
			public void write(int b) throws IOException {
				tee.write(b);
			}
			
		};
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new TeePrintWriter(super.getWriter(), writer);
	}

	public byte[] toByteArray() {
		return bos.toByteArray();
	}

}
