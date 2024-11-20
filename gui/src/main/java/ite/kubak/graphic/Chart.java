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
    private String table;
    private int year;

    public Chart(List<Element> elements, String table, int year){
        List<Double> temp_per = new ArrayList<>();
        List<Integer> temp_num = new ArrayList<>();
        List<String> temp_nam = new ArrayList<>();
        for(Element element : elements){
            if(element.getHosp_number()>0){
                temp_per.add(element.getHosp_percentage());
                temp_num.add(element.getHosp_number());
                temp_nam.add(element.getName());
            }
        }
        this.percentages = temp_per;
        this.numbers = temp_num;
        this.names = temp_nam;
        this.table = table;
        this.year = year;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Parametry wykresu
        int barWidth = 60;
        int spacing = 50;
        int maxHeight = 1000;
        int maxWidth = names.size()*(barWidth+spacing)+100;

        // Rysowanie osi
        graphics.drawLine(50, 350, maxWidth, 350); // Oś X
        graphics.drawLine(50, 350, 50, 50);  // Oś Y

        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString(table+"("+year+")", 55, 30);

        // Rysowanie słupków
        for (int i = 0; i < numbers.size(); i++) {
            int barHeight = (int) ((percentages.get(i) / 300.0) * maxHeight);
            int x = 60 + i * (barWidth + spacing);
            int y = 350 - barHeight;
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, y, barWidth, barHeight);

            // Liczba na słupkach
            if(barHeight>20){
                int textY = y + barHeight / 2;
                graphics.setFont(new Font("Arial", Font.PLAIN, 12));
                graphics.setColor(Color.WHITE);
                graphics.drawString(String.valueOf(numbers.get(i)), x+barWidth/4, textY);
            }

            // Procenty nad słupkami
            graphics.setFont(new Font("Arial", Font.PLAIN, 12));
            graphics.setColor(Color.BLACK);
            String percentageText = String.format("%.1f%%", percentages.get(i));
            graphics.drawString(String.valueOf(percentageText), x+barWidth/4, y-5);

            FontMetrics metrics = graphics.getFontMetrics(new Font("Arial", Font.PLAIN, 10));
            // Podział nazw na linie, jeśli są zbyt długie
            String label = names.get(i);
            int lineHeight = metrics.getHeight();
            int availableWidth = barWidth;
            List<String> wrappedLines = wrapText(label, availableWidth, metrics);

            int labelY = 370;
            graphics.setFont(new Font("Arial", Font.PLAIN, 10));
            graphics.setColor(Color.BLACK);
            for (String line : wrappedLines) {
                int labelX = x + (barWidth - metrics.stringWidth(line)) / 2; // Wyśrodkowanie tekstu
                graphics.drawString(line, labelX, labelY);
                labelY += lineHeight; // Przejdź do następnej linii
            }
        }

    }

    private List<String> wrapText(String text, int maxWidth, FontMetrics metrics) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        for (String word : text.split(" ")) {
            String testLine = currentLine + (currentLine.length() > 0 ? " " : "") + word;
            if (metrics.stringWidth(testLine) > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
            else currentLine.append(currentLine.length() > 0 ? " " : "").append(word);
        }
        if (currentLine.length() > 0) lines.add(currentLine.toString());
        return lines;
    }

}
