package ite.kubak.communication;

import ite.kubak.logic.HttpGet;
import ite.kubak.logic.Parser;
import ite.kubak.model.Hospitality.Info;
import ite.kubak.model.benefit.Benefit;
import ite.kubak.model.benefit.BenefitResponse;
import ite.kubak.model.index.IndexOfTables;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommImpl implements CommInt{
    HttpGet http = new HttpGet();
    Parser parser = new Parser();

    @Override
    public List<Benefit> get_benefits(String URL, String query){
        List<Benefit> all_benefits = new ArrayList<>();
        int page = 1;
        String next_page;
        do {
            String q = URL+"benefits?catalog=1a&benefit="+query+"&page="+page+"&limit=25";
            HttpRequest request = http.create_request(q);
            HttpResponse<String> response = http.get_response(request);
            BenefitResponse benefitResponse = parser.parse(response,BenefitResponse.class);
            all_benefits.addAll(benefitResponse.getData());
            next_page = benefitResponse.getLinks().getNext();
            page++;
        } while (next_page!=null);
        for(Benefit benefit : all_benefits) System.out.println("Nazwa: "+benefit.getName()+" KOD: "+benefit.getCode());
        return all_benefits;
    }

    @Override
    public IndexOfTables get_index(String URL, String name){
        try{
            name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        } catch(Exception e){}
        String q = URL+"index-of-tables?catalog=1a&name="+name;
        HttpRequest request = http.create_request(q);
        HttpResponse<String> response = http.get_response(request);
        IndexOfTables index = parser.parse(response,IndexOfTables.class);
        System.out.println(index.getData().getAttributes().getYears().get(0).getTables().get(0).getLink().getRelated());
        return index;
    }

    @Override
    public Info get_info(String URL){
        String q = URL;
        HttpRequest request = http.create_request(q);
        HttpResponse<String> response = http.get_response(request);
        Info info = parser.parse(response,Info.class);
        System.out.println("XD");
        return info;
    }

}

