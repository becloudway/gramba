package be.cloudway.grambda.runtime.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

public class HttpMockServer {
    private HttpServer httpServer;

    public void createMockServer (int port) throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
    }

    public HttpHandler createHandler(Consumer<HttpExchange> handler) {
        return handler::accept;
    }

    public void addHandler(String endpoint, Consumer<HttpExchange> handler) {
        httpServer.createContext(endpoint, createHandler(handler));
    }

    public void start () {
        httpServer.start();
    }

    public void stop () {
        httpServer.stop(0);
    }

    public String responseBodyToString (HttpExchange httpExchange) throws IOException {
        InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();

        return buf.toString();
    }
}
