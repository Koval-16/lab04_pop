package ite.kubak.graphic;

import ite.kubak.model.Hospitality.Element;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chart extends JPanel {

    private List<Double> percentages;
    private List<Integer> numbers;
    private List<String> names;

    public Chart(List<Element> elements){
        List<Double> temp_per = new ArrayList<>();
        List<Integer> temp_num = new ArrayList<>();
        List<String> temp_nam = new ArrayList<>();
        for(Element element : elements){
            temp_per.add(element.getHosp_percentage());
            temp_num.add(element.getHosp_number());
            temp_nam.add(element.getName());
        }
        this.percentages = temp_per;
        this.numbers = temp_num;
        this.names = temp_nam;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Parametry wykresu
        int barWidth = 50;
        int spacing = 50;
        int maxHeight = 1000;

        // Rysowanie osi
        graphics.drawLine(50, 350, 400, 350); // Oś X
        graphics.drawLine(50, 350, 50, 50);  // Oś Y

        // Rysowanie słupków
        for (int i = 0; i < numbers.size(); i++) {
            int barHeight = (int) ((percentages.get(i) / 300.0) * maxHeight);
            int x = 60 + i * (barWidth + spacing);
            int y = 350 - barHeight;
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, y, barWidth, barHeight);

            // Opisy
            graphics.setColor(Color.BLACK);
            graphics.drawString(names.get(i), x, 370);

            graphics.setColor(Color.BLACK);
            graphics.drawString(String.valueOf(numbers.get(i)), x+barWidth/4, y-5);

            String percentageText = String.format("%.1f%%", percentages.get(i));
            int textY = y + barHeight / 2;
            graphics.setColor(Color.WHITE);
            graphics.drawString(percentageText, x+barWidth/4, textY);
        }

    }

}
