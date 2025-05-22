package co.edu.uniquindio.poo.proyectofinal.Servicios;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ServicioGenerarQr {

    public File generarQR(String contenido, String nombreArchivo) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;

        BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath("qr_generados/" + nombreArchivo + ".png");
        File archivoQR = path.toFile();
        archivoQR.getParentFile().mkdirs(); // Crear carpeta si no existe

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return archivoQR;
    }
}
