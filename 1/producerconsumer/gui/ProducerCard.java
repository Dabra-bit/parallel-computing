package producerconsumer.gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;

public class ProducerCard extends JPanel {
    public static final int PRODUCING = 1;
    public static final int SLEEPING = 2;
    private static final String PRODUCING_IMG = "/Users/dberrosp/ceti/parallel-computing/1/producerconsumer/gui/images/producer.jpg";
    private static final String SLEEPING_IMG = "/Users/dberrosp/ceti/parallel-computing/1/producerconsumer/gui/images/sleep.jpg";

    private String name;
    private BufferedImage image;

    private JLabel lblName;
    private JLabel lblImg;

    public ProducerCard(String name) {
        super();

        this.name = name;
        image = readImg(PRODUCING_IMG);

        initView();
    }

    public void updateCard(int state) {

        switch(state) {
            case PRODUCING:
                image = readImg(PRODUCING_IMG);
                break;
            case SLEEPING:
                image = readImg(SLEEPING_IMG);
                break;
        }

        lblImg.setIcon(getIconFromImg());
    }

    private void initView() {
        lblName = new JLabel(name);
        lblImg = new JLabel(getIconFromImg());

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        add(lblName);
        add(lblImg);
    }

    private BufferedImage readImg(String pathname) {
        BufferedImage tmpImg = null;
        try {
            tmpImg = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpImg;
    }

    private ImageIcon getIconFromImg() {
        return new ImageIcon(
            image.getScaledInstance(80, 80, Image.SCALE_SMOOTH)
            );
    }
}
