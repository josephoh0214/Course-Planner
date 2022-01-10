import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        HttpContext context = server.createContext("/");
        HttpContext contextCourseTracker = server.createContext("/courses-tracker");
        context.setHandler(ServerApp::handlerRequest);
        contextCourseTracker.setHandler(ServerApp::handlerCourseTrackerRequest);
        server.start();
    }

    private static void handlerRequest(HttpExchange exchange) throws IOException {
        StringBuilder response = new StringBuilder("");
        int line;
        File template = new File("src/main/resources/index.html");
        InputStream inputStream = new FileInputStream(template);
        OutputStream outputStream = exchange.getResponseBody();
        while ((line = inputStream.read()) != -1) {
            response.append((char) line);
        }
        exchange.getResponseHeaders().set("content-type", "text/html");
        exchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.toString().getBytes());
        outputStream.close();
    }

    private static void handlerCourseTrackerRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("GET")) {
            throw new IOException("Wrong Http method");
        }
        Map<String, String> params = httpGetParams(exchange);
        StringBuilder response = new StringBuilder("");
        response.append("{\"response1\": \"")
                .append(preReq(params.getOrDefault("major", "")))
                .append("\",")
                .append("\"response2\": \"")
                .append(gradReq(params.getOrDefault("current_major", "")))
                .append("\",")
                .append("\"response3\": \"")
                .append(infoDegreeOps(params.getOrDefault("degree", "")))
                .append("\"}");

        OutputStream outputStream = exchange.getResponseBody();
        exchange.getResponseHeaders().set("content-type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        outputStream.write(response.toString().getBytes());
        outputStream.close();
    }

    private static String preReq(String major) throws FileNotFoundException {
        Scanner dataset = new Scanner(new File("src/main/resources/informatics_prereq.csv"));
        dataset.useDelimiter(",");
        String department = dataset.next();
        StringBuilder response = new StringBuilder();
        if (major.equalsIgnoreCase(department)) {
            response.append("Pre-requisite courses for " + major + ":,");
        }
        int i = 1;
        while (dataset.hasNext()) {
            response.append(dataset.next().replaceAll("\n","")).append(",");
            i++;
        }

        return response.toString();

    }

    private static String gradReq(String ans) throws FileNotFoundException {
        StringBuilder response = new StringBuilder("");
         switch (ans) {
             case "yes": {
                 Scanner dataset = new Scanner(new File("src/main/resources/informatics_gradreq.csv"));
                 dataset.useDelimiter(",");
                 Set<String> coreCourses = new TreeSet<>();
                 while (dataset.hasNextLine()) {
                     coreCourses.add(dataset.nextLine());
                 }
                 int i = 1;
                 for (String courses : coreCourses) {
                     response.append(i).append(". ").append(courses).append(",");
                     i++;
                 }
                 break;
             }
             default: {
                 response.append("Good luck on your application!");
                 break;
             }
         }
         return response.toString();
    }

    public static String infoDegreeOps(String number) throws FileNotFoundException {
        Scanner dataset = new Scanner(new File("src/main/resources/informatics_degree.csv"));
        StringBuilder response = new StringBuilder("");
        dataset.useDelimiter(",");
        if (number.equals("6")) {
            response.append("Students may choose to develop their own concentration, with approval");
            response.append("from the academic adviser. Student-designed concentrations are created");
            response.append("out of a list of approved courses and also result in the");
            response.append("Bachelor of Science degree.");
        } else {
            while (!dataset.nextLine().equals(number)) {
                dataset.nextLine();
            }
            String required = dataset.nextLine();
            response.append(required);
        }
        return response.toString();
    }

    public static Map<String, String> httpGetParams(HttpExchange exchange) {
        Map<String, String> params = new HashMap<>();
        for (String param: exchange.getRequestURI().getQuery().split("&")) {
            String splitParam[] = param.split("=");
            params.put(splitParam[0],splitParam[1]);
        }
        return params;
    }

}
