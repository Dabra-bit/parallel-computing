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

public class ConsumerCard extends JPanel {
    public static final int CONSUMING = 1;
    public static final int SLEEPING = 2;
    private static final String CONSUMING_IMG = "/Users/dberrosp/ceti/parallel-computing/1/producerconsumer/gui/images/consumer.png";
    private static final String SLEEPING_IMG = "/Users/dberrosp/ceti/parallel-computing/1/producerconsumer/gui/images/sleep.jpg";

    private String name;
    private BufferedImage image;

    private JLabel lblName;
    private JLabel lblImg;

    public ConsumerCard(String name) {
        super();

        this.name = name;
        image = readImg(CONSUMING_IMG);

        initView();
    }

    public void updateCard(int state) {

        switch(state) {
            case ConsumerCard.CONSUMING:
                image = readImg(ConsumerCard.CONSUMING_IMG);
                break;
            case ConsumerCard.SLEEPING:
                image = readImg(ConsumerCard.SLEEPING_IMG);
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
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private ImageIcon getIconFromImg() {
        return new ImageIcon(
            image.getScaledInstance(80, 80, Image.SCALE_SMOOTH)
            );
    }
}
