package github.group.details;

import javax.swing.*;

abstract class AbstractDetailsView {
    protected JFrame frame;
    protected JLabel detaillabel, makelabel, typelabel, stickerlabel, colorlabel;
    protected JTextArea makearea, linearea, typearea, stickerarea, colorarea, expiryarea, registrationarea;
    protected DetailsController detcon;
    protected JLabel backLabel, quitLabel, userLabel;

    abstract void setDetailsController(DetailsController detcon);
    abstract void detailsView(JFrame frame, String username, String make, String type, String color, String regDate, String expDate, String sticker);
    abstract void initView(String username, String make, String type, String color, String regDate, String expDate, String sticker);
}
