package proto;

import damas.DamasBoard;

import javax.swing.*;
import java.awt.*;

public class GamePane  extends JFrame{
    public static void main(String[] args) {
        GamePane gp = new GamePane();

        DamasBoard d = new DamasBoard();
        gp.showMessageDialog(d.toString());
    }

    private String title;
    private String font = "sans-serif";

    private static final int MIN_WIDTH = 500;
    private static final int MIN_HEIGHT = 500;


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
            "</table>\n" +
            "</body>\n" +
            "</html>";

    public GamePane() {
        UIManager.put("OptionPane.minimumSize",new Dimension(MIN_WIDTH, MIN_HEIGHT));
        title = "Juegos Reunidos";
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void showMessageDialog(String msg){
        JLabel label = new JLabel(msg);
        label.setFont(new Font(font, Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, label, title, JOptionPane.PLAIN_MESSAGE);
    }

    public int showOptionsDialog(String msg, String[] options){
        JLabel label = new JLabel(msg);
        label.setFont(new Font(font, Font.PLAIN, 14));
        return JOptionPane.showOptionDialog(this, label, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    }

    public Object showInputDialog(String msg, Object[] options){
        JLabel label = new JLabel(msg);
        label.setFont(new Font(font, Font.PLAIN, 14));
        return JOptionPane.showInputDialog(this, label, title, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

}
