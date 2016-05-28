package local.diplom.service.model;

import javax.persistence.*;

/**
 * Сущность изображения
 */
@Entity
public class Image {
    // Идентификатор изображения целочисленное число
    private Long id;
    // Строка тип изображения
    private String mimeType;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    // файл изображения
    protected byte[] imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }
}
