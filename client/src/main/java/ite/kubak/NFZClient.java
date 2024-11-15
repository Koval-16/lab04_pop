package ite.kubak;

import ite.kubak.logic.HttpGet;
import ite.kubak.logic.Parser;
import ite.kubak.model.benefit.Benefit;
import ite.kubak.model.benefit.BenefitResponse;
import ite.kubak.model.index.IndexOfTables;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class NFZClient {
    public static void main(String[] args) {
        HttpGet http = new HttpGet();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter: ");
        String string = scanner.nextLine();


        String URL = Website.URL+"benefits?catalog=1a&benefit="+string+"&limit=25";
        HttpRequest request = http.create_request(URL);
        HttpResponse<String> response = http.get_response(request);
        System.out.println("Status HTTP: " + response.statusCode());
        System.out.println("Treść odpowiedzi:");
        System.out.println(response.body());
        BenefitResponse benefitResponse = parser.parse(response,BenefitResponse.class);
        List<Benefit> benefits = benefitResponse.getData();
        for(Benefit benefit : benefits) System.out.println("Nazwa: "+benefit.getName()+" KOD: "+benefit.getCode());


        System.out.print("Nazwa: ");
        String name = scanner.nextLine();
        try{
            name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        } catch(Exception e){}
        System.out.println(name);
        URL = Website.URL+"index-of-tables?catalog=1a&name="+name;
        request = http.create_request(URL);
        response = http.get_response(request);
        System.out.println("Status HTTP: " + response.statusCode());
        System.out.println("Treść odpowiedzi:");
        System.out.println(response.body());
        IndexOfTables index = parser.parse(response,IndexOfTables.class);
        System.out.println(index.getData().getAttributes().getYears().get(0).getTables().get(0).getAttributes().getHeader());



        /*
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nazwa: ");
        String name = scanner.nextLine();
        try{
            name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        } catch(Exception e){}
        try{
            String URL = Website.URL;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL+"index-of-tables?catalog=1a&name="+name+"&year="+year)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status HTTP: " + response.statusCode());
            System.out.println("Treść odpowiedzi:");
            System.out.println(response.body());
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("ID: ");
        String id = scanner.nextLine();
        try{
            String URL = Website.URL;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL+"hospitalizations-by-patient-gender/"+id)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status HTTP: " + response.statusCode());
            System.out.println("Treść odpowiedzi:");
            System.out.println(response.body());
        } catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
