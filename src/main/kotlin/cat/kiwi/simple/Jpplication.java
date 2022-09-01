package cat.kiwi.simple;

import cat.kiwi.simple.context2.SimpleContext;
import cat.kiwi.simple.context2.SimpleHttpServer;

public class Jpplication {
    public static void main(String[] args) {
        SimpleContext context = SimpleContext.simpleContext();
        SimpleHttpServer simpleHttpServer =  context.createHttpServer();
        simpleHttpServer.getAddress();

    }
}
