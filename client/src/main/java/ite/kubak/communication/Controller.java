package ite.kubak.communication;

import ite.kubak.model.Hospitality.Element;
import ite.kubak.model.Hospitality.Info;
import ite.kubak.model.index.IndexOfTables;
import ite.kubak.model.index.Table;
import ite.kubak.model.index.Year;
import ite.kubak.temp.Website;
import ite.kubak.model.benefit.Benefit;

import java.util.ArrayList;
import java.util.List;

public class Controller implements Listener{

    private final CommImpl comm = new CommImpl();
    private IndexOfTables index;

    public Controller(){}

    @Override
    public List<String> get_benefits(String query){
        List<Benefit> benefits = comm.get_benefits(Website.URL,query);
        List<String> names = new ArrayList<>();
        for(Benefit benefit : benefits) names.add(benefit.getName());
        return names;
    }

    private void ind(String name){
        index = comm.get_index(Website.URL, name);
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
                    System.out.println(tbl.getAttributes().getHeader());
                    if(tbl.getAttributes().getHeader().equals(table)){
                        return comm.get_info(tbl.getLink().getRelated()).getElements();
                    }
                }
            }
        }
        return null;
    }

}
