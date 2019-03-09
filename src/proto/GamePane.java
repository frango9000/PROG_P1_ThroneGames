package proto;

import damas.Damas;
import damas.DamasBoard;

import javax.swing.*;
import java.awt.*;

public class GamePane {
    public static void main(String[] args) {
        GamePane gp = new GamePane();

        DamasBoard d = new DamasBoard();
        gp.showGamePane(d.toString());
    }

    private String title;
    private JOptionPane frame;
    private String font;

    String html = "<html>This is how to get:"
            + "<ul><li><i>italics</i> and "
            + "<li><b>bold</b> and "
            + "<li><u>underlined</u>...</ul></html>";
    String table = "<html>\n" +
            "<style>\n" +
            "table{border:solid 5px black;\n" +
            "border-collapse: collapse;\n" +
            "border-spacing: 1px;}\n" +
            "td {border:1px solid black;\n" +
            "width: 50px;\n" +
            "height: 50px;}\n" +
            "td.w{background-color: #eeeeee;}\n" +
            "td.b{background-color: #555555;}\n" +
            "th{width: 18px}\n" +
            "</style>\n" +
            "<body>\n" +
            "<table>\n" +
            "<tr class=\"colheader\">\n" +
            "<th> </th>\n" +
            "<th>A</th>\n" +
            "<th>B</th>\n" +
            "<th>C</th>\n" +
            "<th>D</th>\n" +
            "<th>E</th>\n" +
            "<th>F</th>\n" +
            "<th>G</th>\n" +
            "<th>H</th>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<th>8</th>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<th>7</th>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +
            "<td class=\"b\"></td>\n" +
            "<td class=\"w\"></td>\n" +

            "</tr>\n" +
            "</table>\n" +
            "</body>\n" +
            "</html>";

    public GamePane() {
        UIManager.put("OptionPane.minimumSize",new Dimension(500,500));

        title = "Juegos Reunidos";
        frame = new JOptionPane();
        String font = "sans-serif";

    }

    public void showGamePane(String msg){
        JLabel label = new JLabel(msg);
        label.setFont(new Font(font, Font.PLAIN, 14));
        JOptionPane.showMessageDialog(frame, label, title, JOptionPane.PLAIN_MESSAGE);
    }
}
