package ite.kubak.communication;

import ite.kubak.model.Hospitality.Element;
import ite.kubak.model.index.IndexOfTables;
import ite.kubak.model.index.Table;
import ite.kubak.model.index.Year;
import ite.kubak.model.benefit.Benefit;

import java.util.ArrayList;
import java.util.List;

public class Controller implements Listener{

    private final CommImpl comm = new CommImpl();
    private IndexOfTables index;
    public static final String URL = "https://api.nfz.gov.pl/app-stat-api-jgp/";

    public Controller(){}

    @Override
    public List<String> get_benefits(String query){
        List<Benefit> benefits = comm.get_benefits(URL,query);
        List<String> names = new ArrayList<>();
        for(Benefit benefit : benefits) names.add(benefit.getName());
        return names;
    }

    private void ind(String name){
        index = comm.get_index(URL, name);
    }

    @Override
    public List<Integer> get_index(String name){
        ind(name);
        List<Year> years = index.getData().getAttributes().getYears();
        List<Integer> numers = new ArrayList<>();
        for(Year year : years) numers.add(year.getYear());
        return numers;
    }

    @Override
    public List<String> get_tables(int year){
        List<Year> years = index.getData().getAttributes().getYears();
        List<String> tables = new ArrayList<>();
        for(Year yr : years) if(yr.getYear()==year){
            for(int i=0; i<yr.getTables().size(); i++){
                if(yr.getTables().get(i).getType().equals("hospitalization-by-gender") ||
                        yr.getTables().get(i).getType().equals("hospitalization-by-admission") ||
                        yr.getTables().get(i).getType().equals("hospitalization-by-discharge") ||
                        yr.getTables().get(i).getType().equals("hospitalization-by-age"))
                    tables.add(yr.getTables().get(i).getAttributes().getHeader());
            }
        }
        return tables;
    }

    @Override
    public List<Element> get_chart(int year, String table){
        List<Year> years = index.getData().getAttributes().getYears();
        for(Year yr : years) {
            if(yr.getYear()==year){
                List<Table> tables = yr.getTables();
                for(Table tbl : tables) {
                    if(tbl.getAttributes().getHeader().equals(table)){
                        List<Element> sorted = comm.get_info(tbl.getLinks().getRelated()).getData().getAttributes().getElements();
                        for(int i=0; i<sorted.size()-1; i++){
                            for(int j=i+1; j<sorted.size(); j++){
                                if(sorted.get(i).getCode()>sorted.get(j).getCode()){
                                    Element temp = sorted.get(i);
                                    sorted.set(i, sorted.get(j));
                                    sorted.set(j, temp);
                                }
                            }
                        }
                        return sorted;
                    }
                }
            }
        }
        return null;
    }

}
