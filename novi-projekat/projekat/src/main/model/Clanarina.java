package main.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;

public class Clanarina {
    private LocalDate datumPocetka;
    private LocalDate datumIsteka;
    private String QRkod;
    private String qrImagePath; // putanja do QR kod slike

 

    public Clanarina(String clanEmail) {
        super();
        this.datumPocetka = LocalDate.now();
        this.datumIsteka = datumPocetka.plusMonths(1);
        this.QRkod = generisiQRKod(clanEmail);
        generisiQRSliku();
    }

    public Clanarina(LocalDate datumPocetka, LocalDate datumIsteka, String qRkod) {
        super();
        this.datumPocetka = datumPocetka;
        this.datumIsteka = datumIsteka;
        this.QRkod = qRkod;
        generisiQRSliku();
    }


    // Metoda za generisanje QR koda sa email-om člana
    private String generisiQRKod(String email) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return "CLANARINA-" + email + "-" + timestamp;
    }

    // Metoda za generisanje QR kod slike
    private void generisiQRSliku() {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            
            // Kreiranje QR kod podataka (JSON format)
            String qrData = String.format(
                "{\"kod\":\"%s\",\"pocetak\":\"%s\",\"istek\":\"%s\"}", 
                QRkod, datumPocetka.toString(), datumIsteka.toString()
            );
            
            //enkodiram podatke u zavisnoti od QrKoda, datuma pocetka i datuma isteka
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 300, 300);
           
            
            BufferedImage qrImage = createQRImage(bitMatrix);
            
            // Kreiranje direktorijuma ako ne postoji
            File qrDir = new File("qr_kodovi");
            if (!qrDir.exists()) {
                qrDir.mkdirs();
            }
            
            // Čuvanje slike
            String fileName = "QR_" + QRkod.replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            File qrFile = new File(qrDir, fileName);
            ImageIO.write(qrImage, "PNG", qrFile);
            
            this.qrImagePath = qrFile.getAbsolutePath();
            System.out.println("QR kod slika kreirana: " + qrImagePath);
            
        } catch (WriterException | IOException e) {
            System.err.println("Greška pri kreiranju QR koda: " + e.getMessage());
            this.qrImagePath = null;
        }
    }
    
    // Metoda za kreiranje BufferedImage iz BitMatrix
    private BufferedImage createQRImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        
        // Postavi belu pozadinu
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        
        // Crtam crne piksele za QR kod
        //Tamo gde je true ide crni piksel, ako je false ostaje beli
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (matrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }
        
        //oslobadjam resurse, sprecavam memory leaking
        graphics.dispose();
        return image;
    }



    // Metoda za dobijanje QR slike kao BufferedImage
    public BufferedImage getQRImage() {
        if (qrImagePath != null) {
            try {
                return ImageIO.read(new File(qrImagePath));
            } catch (IOException e) {
                System.err.println("Greška pri učitavanju QR slike: " + e.getMessage());
            }
        }
        return null;
    }

    // Getteri i setteri
    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getQRkod() {
        return QRkod;
    }

    public void setQRkod(String qRkod) {
        this.QRkod = qRkod;
    }

    public String getQrImagePath() {
        return qrImagePath;
    }

    @Override
    public String toString() {
        return "Clanarina [datumPocetka=" + datumPocetka + ", datumIsteka=" + datumIsteka + 
               ", QRkod=" + QRkod + ", qrImagePath=" + qrImagePath + "]";
    }
}